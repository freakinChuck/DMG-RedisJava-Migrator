package hs16.dmg.javaredis.migrator.redisimporter;

import hs16.dmg.javaredis.migrator.CitizenNumberEntry;
import hs16.dmg.javaredis.migrator.Town;
import hs16.dmg.javaredis.migrator.database.DbManager;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Objects;

/**
 * Created by silvio on 09.12.16.
 */
public class Importer {

    private final String allKantons = "AllKantons";
    private final String kantonTownListFormat = "Kanton-%1$s:Towns";
    private final String townFormat = "Town-%1$s:%2$s";
    public void ImportTowns(List<Town> towns)
    {
        Jedis db = DbManager.getDb();
        db.del(allKantons);
        Object[] kantons = (towns.stream().map(t -> t.getKanton()).distinct().toArray());
        for (Object kantonObject : kantons) {
            String kanton = (String)kantonObject;
            db.lpush(allKantons, kanton);

            Object[] allBfsNumbersByKanton =
                    (towns.
                                stream()
                                    .filter(t -> t.getKanton().equals(kanton))
                                    .map(t -> t.getBfsNumber())
                                .toArray()
                    );

            for (Object bfsNumberObject : allBfsNumbersByKanton) {
                Integer bfsNumber = (Integer)bfsNumberObject;
                db.set(
                        String.format(kantonTownListFormat, kanton),
                        Integer.toString(bfsNumber)
                );
            }

        }

        for (Town town : towns) {
            db.set(
                    String.format(townFormat, town.getBfsNumber(), "Kanton"),
                    town.getKanton()
            );
            db.set(
                    String.format(townFormat, town.getBfsNumber(), "TownName"),
                    town.getTownName()
            );
        }


    }

    private final String statisticFormat = "Statistic-%1$s:%2$s";
    public void ImportStatistic(List<CitizenNumberEntry> entries)
    {
        Jedis db = DbManager.getDb();
        for (CitizenNumberEntry entry:
             entries) {

            db.set(
                    String.format(statisticFormat, entry.getBfsNumber(), "Number"),
                    Integer.toString(entry.getNumberOfCitizen())
            );
            db.set(
                    String.format(statisticFormat, entry.getBfsNumber(), "TownName"),
                    entry.getTownName()
            );

        }
    }

}

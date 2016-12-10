package hs16.dmg.javaredis;

import hs16.dmg.javaredis.migrator.CitizenNumberEntry;
import hs16.dmg.javaredis.migrator.Town;
import hs16.dmg.javaredis.migrator.migrationcsvparser.CsvParser;
import hs16.dmg.javaredis.migrator.redisimporter.Importer;
import redis.clients.jedis.Jedis;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String townListPath = "TODO / DROPBOX";
        final String citizenEntryPath = "TODO / DROPBOX";

        CsvParser parser = new CsvParser();
        Importer importer = new Importer();

        List<Town> townList = parser.ParseTowns(townListPath);
        List<CitizenNumberEntry> citizenList = parser.ParseCitizenNumbers(citizenEntryPath);

        importer.ImportTowns(townList);

        //Jedis db = new Jedis("192.168.99.100", 32770);
        //String ping = db.ping();

        //db.set("TestString", "Redis");


        //System.out.print(ping);
        //System.out.print(db.get("TestString"));
    }
}

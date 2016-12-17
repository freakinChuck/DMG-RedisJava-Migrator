package hs16.dmg.javaredis;

import hs16.dmg.javaredis.migrator.CitizenNumberEntry;
import hs16.dmg.javaredis.migrator.Town;
import hs16.dmg.javaredis.migrator.migrationcsvparser.CsvParser;
import hs16.dmg.javaredis.migrator.redisimporter.Importer;
import redis.clients.jedis.Jedis;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String townListPath = "/Users/silvio/Dropbox/DMG.H16 Gruppe 9/_NoSQL Projekt/Datenbasis/PostleitzahlverzeichnisUTF8.csv";
        final String citizenEntryPath = "/Users/silvio/Dropbox/DMG.H16 Gruppe 9/_NoSQL Projekt/Datenbasis/EinwohnerstatistikUTF8.csv";

        CsvParser parser = new CsvParser();

        List<Town> townList = parser.ParseTowns(townListPath);
        List<CitizenNumberEntry> citizenList = parser.ParseCitizenNumbers(citizenEntryPath);

        Importer importer = new Importer();

        importer.ImportTowns(townList);
        importer.ImportStatistic(citizenList);
    }
}

package hs16.dmg.javaredis.migrator.migrationcsvparser;

import hs16.dmg.javaredis.migrator.CitizenNumberEntry;
import hs16.dmg.javaredis.migrator.Town;
import sun.nio.cs.StandardCharsets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by silvio on 09.12.16.
 */
public class CsvParser {

    public List<Town> ParseTowns(String townFilePath){

        List<Town> townList = new LinkedList<>();
        List<String> fileLines = GetFileContent(townFilePath);

        if (fileLines.isEmpty())
            return townList;

        String headerLine = fileLines.remove(0);

        List<Integer> alreadyUsedBfsCodes = new LinkedList<>();

        for (String line:
             fileLines) {

            Town town = ParseTown(line);
            if (!alreadyUsedBfsCodes.contains(town.getBfsNumber())) {
                townList.add(town);
                alreadyUsedBfsCodes.add(town.getBfsNumber());
            }
        }
        return townList;
    }

    private Town ParseTown(String line) {

        final int kantonIndex = 0;
        final int bfsCodeIndex = 1;
        final int townNameIndex = 2;


        String[] items = line.split(";");

        if (items.length < 5)
            return null;

        Town newItem = new Town();

        newItem.setBfsNumber(Integer.parseInt(items[bfsCodeIndex]));
        newItem.setKanton(items[kantonIndex]);
        newItem.setTownName(items[townNameIndex]);

        return newItem;
    }

    public List<CitizenNumberEntry> ParseCitizenNumbers(String citizenNumberFilePath){

        List<CitizenNumberEntry> citizenNumberList = new LinkedList<>();
        List<String> fileLines = GetFileContent(citizenNumberFilePath);

        if (fileLines.isEmpty())
            return citizenNumberList;

        String headerLine = fileLines.remove(0);


        for (String line:
                fileLines) {

            citizenNumberList.add(ParseCitizenNumberEntry(line));

        }
        return citizenNumberList;
    }

    private CitizenNumberEntry ParseCitizenNumberEntry(String line) {


        String[] items = line.split(";");

        if (items.length < 3)
            return null;

        final int bfsNumberIndex = 0;
        final int townNameIndex = 1;
        final int numberOfCitizenIndex = 2;


        CitizenNumberEntry newItem = new CitizenNumberEntry();

        newItem.setBfsNumber(Integer.parseInt(items[bfsNumberIndex]));
        newItem.setTownName(items[townNameIndex]);
        newItem.setNumberOfCitizen(Integer.parseInt(items[numberOfCitizenIndex]));


        return newItem;
    }

    private List<String> GetFileContent(String townFilePath) {
        try {
            Path filePath = Paths.get(townFilePath);
            return Files.readAllLines(filePath);
        }
        catch(IOException ex){
            return new LinkedList<>();
        }
    }

}

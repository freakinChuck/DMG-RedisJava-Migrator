package hs16.dmg.javaredis.migrator;

/**
 * Created by silvio on 09.12.16.
 */
public class CitizenNumberEntry {

    private int bfsNumber;
    private String townName;
    private int numberOfCitizen;

    public int getBfsNumber() {
        return bfsNumber;
    }

    public void setBfsNumber(int bfsNumber) {
        this.bfsNumber = bfsNumber;
    }

    public int getNumberOfCitizen() {
        return numberOfCitizen;
    }

    public void setNumberOfCitizen(int numberOfCitizen) {
        this.numberOfCitizen = numberOfCitizen;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}

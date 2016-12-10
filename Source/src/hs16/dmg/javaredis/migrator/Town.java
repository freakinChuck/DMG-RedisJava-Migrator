package hs16.dmg.javaredis.migrator;

/**
 * Created by silvio on 09.12.16.
 */
public class Town {
    private String kanton;
    private int bfsNumber;
    private String townName;

    public String getKanton() {
        return kanton;
    }

    public void setKanton(String kanton) {
        this.kanton = kanton;
    }

    public int getBfsNumber() {
        return bfsNumber;
    }

    public void setBfsNumber(int bfsNumber) {
        this.bfsNumber = bfsNumber;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

}

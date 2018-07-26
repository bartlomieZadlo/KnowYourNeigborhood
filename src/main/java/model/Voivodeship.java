package model;

public class Voivodeship {
    private String name;
    private int index;
    private String locationType;

    public Voivodeship(String name, int index, String locationType) {
        this.name = name;
        this.index = index;
        this.locationType = locationType;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public String getLocationType() {
        return locationType;
    }
}

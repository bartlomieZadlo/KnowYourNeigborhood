package model;

public class County {
    private String name;
    private int voidIndex;
    private int countIndex;
    private String locType;

    public County(String name, int voidIndex, int countIndex, String locType) {
        this.name = name;
        this.voidIndex = voidIndex;
        this.countIndex = countIndex;
        this.locType = locType;
    }

    public String getName() {
        return name;
    }

    public int getVoidIndex() {
        return voidIndex;
    }

    public int getCountIndex() {
        return countIndex;
    }

    public String getLocType() {
        return locType;
    }
}

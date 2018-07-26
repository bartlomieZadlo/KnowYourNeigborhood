package model;

public class Community {
    private String name;
    private int voidIndex;
    private int countIndex;
    private int communityIndex;
    private String locType;

    public Community(String name, int voidIndex, int countIndex, int communityIndex, String locType) {
        this.name = name;
        this.voidIndex = voidIndex;
        this.countIndex = countIndex;
        this.communityIndex = communityIndex;
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

    public int getCommunityIndex() {
        return communityIndex;
    }

    public String getLocType() {
        return locType;
    }
}

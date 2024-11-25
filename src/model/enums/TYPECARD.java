package model.enums;


public enum TYPECARD {
    SWORD("sword"),
    GOBLET("goblet"),
    GOLDEN_COIN("coin"),
    CUP("cup");

    private final String name;

    TYPECARD(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean compareType(TYPECARD oType){
        return oType.compareTo(TYPECARD.CUP) == 0 || oType.compareTo(this) == 0;
    }

}
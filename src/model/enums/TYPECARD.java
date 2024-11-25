package model.enums;


public enum TYPECARD {
    SWORD("espada"),
    GOBLET("basto"),
    GOLDEN_COIN("oro"),
    CUP("copa");

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
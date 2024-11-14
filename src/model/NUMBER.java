package model;

public enum NUMBER {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    ELEVEN,
    TWELVE;


    @Override
    public String toString() {
        return "" + (this.ordinal() + 1);
    }
}

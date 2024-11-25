package model.enums;

public enum NUMBER {
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine"),
    TEN("ten"),
    ELEVEN("eleven"),
    TWELVE("twelve");

    private final String name;

    // Constructor para inicializar el nombre
    NUMBER(String name) {
        this.name = name;
    }

    // Método para obtener el nombre
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "" + (this.ordinal() + 1);
    }
}

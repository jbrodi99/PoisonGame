package model.enums;

public enum NUMBER {
    ONE("uno"),
    TWO("dos"),
    THREE("tres"),
    FOUR("cuatro"),
    FIVE("cinco"),
    SIX("seis"),
    SEVEN("siete"),
    EIGHT("ocho"),
    NINE("nueve"),
    TEN("diez"),
    ELEVEN("once"),
    TWELVE("doce");

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

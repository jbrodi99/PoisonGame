package model;

public interface ICard {
    NUMBER getValue();

    TYPECARD getTypeCard();

    Boolean isType(TYPECARD oType);

    Boolean isMayorTen();
}

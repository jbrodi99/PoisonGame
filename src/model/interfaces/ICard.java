package model.interfaces;

import model.enums.NUMBER;
import model.enums.TYPECARD;

public interface ICard {
    NUMBER getValue();
    TYPECARD getTypeCard();
    Boolean isType(TYPECARD oType);
    Boolean isMayorTen();
}

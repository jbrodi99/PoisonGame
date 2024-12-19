package model.logic;

import model.enums.NUMBER;
import model.enums.TYPECARD;
import model.interfaces.ICard;

import java.io.Serializable;

public class Card implements ICard, Serializable {
    private final NUMBER number;
    private final TYPECARD typeCard;

    public Card(NUMBER number, TYPECARD typeCard) {
        this.number = number;
        this.typeCard = typeCard;
    }

    @Override
    public NUMBER getValue() {
        return number;
    }

    @Override
    public TYPECARD getTypeCard() {
        return typeCard;
    }

    @Override
    public Boolean isType(TYPECARD oType){
        return getTypeCard().compareTo(oType) == 0;
    }

    @Override
    public Boolean isMayorTen(){
        return  number.compareTo(NUMBER.TEN) >= 0;
    }
}

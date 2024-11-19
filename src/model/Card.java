package model;

/**
 * Inmutable class Card. Used from desk to play the game.
 * Only exist one couple of "type_card : value"
 *
 * @author Rodriguez Juan Cruz
 * @version 1.0
 */
public class Card implements ICard {
    private NUMBER number;
    private TYPECARD typeCard;

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

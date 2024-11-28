package model.factorys;

import model.enums.NUMBER;
import model.enums.TYPECARD;
import model.interfaces.ICard;
import model.logic.Card;

public class CardFactory implements ICardFactory{
    @Override
    public ICard createCard(NUMBER number, TYPECARD typecard) {
        return new Card(number,typecard);
    }
}

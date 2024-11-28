package model.factorys;

import model.enums.NUMBER;
import model.enums.TYPECARD;
import model.interfaces.ICard;

public interface ICardFactory {
    ICard createCard(NUMBER number, TYPECARD typecard);
}

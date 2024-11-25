package model.validator;

import model.enums.TYPECARD;
import model.interfaces.ICard;
import model.interfaces.IValidator;

import java.io.Serializable;

public class ValidatorGoblet implements IValidator<ICard>, Serializable {

    @Override
    public Boolean validate(ICard card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.CUP || type == TYPECARD.GOBLET;
    }
}

package model;

public class ValidatorSword implements IValidator<ICard> {

    @Override
    public Boolean validate(ICard card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.SWORD || type == TYPECARD.CUP;
    }
}

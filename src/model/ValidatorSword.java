package model;

public class ValidatorSword implements IValidator<Card> {

    @Override
    public Boolean validate(Card card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.SWORD || type == TYPECARD.CUP;
    }
}

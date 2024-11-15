package model;

public class ValidatorGoblet implements IValidator<Card> {

    @Override
    public Boolean validate(Card card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.CUP || type == TYPECARD.GOBLET;
    }
}

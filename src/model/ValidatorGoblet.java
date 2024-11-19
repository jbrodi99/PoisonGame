package model;

public class ValidatorGoblet implements IValidator<ICard> {

    @Override
    public Boolean validate(ICard card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.CUP || type == TYPECARD.GOBLET;
    }
}

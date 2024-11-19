package model;

public class ValidatorGoldenCoin implements IValidator<ICard> {
    @Override
    public Boolean validate(ICard card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.GOLDEN_COIN || type == TYPECARD.CUP;
    }
}

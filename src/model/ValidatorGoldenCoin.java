package model;

public class ValidatorGoldenCoin implements IValidator<Card> {
    @Override
    public Boolean validate(Card card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.GOLDEN_COIN || type == TYPECARD.CUP;
    }
}

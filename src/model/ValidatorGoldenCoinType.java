package model;

public class ValidatorGoldenCoinType implements IValidatorType{
    @Override
    public Boolean validType(Card card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.GOLDEN_COIN || type == TYPECARD.CUP;
    }
}

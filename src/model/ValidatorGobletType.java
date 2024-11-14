package model;

public class ValidatorGobletType implements IValidatorType{

    @Override
    public Boolean validType(Card card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.CUP || type == TYPECARD.GOBLET;
    }
}

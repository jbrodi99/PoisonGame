package model;

public class ValidatorSwordType implements IValidatorType{

    @Override
    public Boolean validType(Card card) {
        TYPECARD type = card.getTypeCard();
        return type == TYPECARD.SWORD || type == TYPECARD.CUP;
    }
}

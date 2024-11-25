package model.validator;

import model.interfaces.IValidator;

import java.io.Serializable;

public class ValidatorLimitPoints implements IValidator<Integer>, Serializable {

    public final static int MIN_POINTS = 1;
    public final static int MAX_POINTS = 50;

    @Override
    public Boolean validate(Integer limitPoints) {
        return (limitPoints >= MIN_POINTS && limitPoints <= MAX_POINTS);
    }
}

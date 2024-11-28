package model.validator;

import java.io.Serializable;

public class ValidatorLimitPoints implements IValidator<Integer>, Serializable {

    public final static int MIN_POINTS = 1; //TODO: test value, in real game use min value :10
    public final static int MAX_POINTS = 50;

    @Override
    public Boolean validate(Integer limitPoints) {
        return (limitPoints >= MIN_POINTS && limitPoints <= MAX_POINTS);
    }
}

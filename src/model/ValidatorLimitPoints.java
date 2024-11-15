package model;

public class ValidatorLimitPoints implements IValidator<Integer>{

    private final int MIN_POINTS = 10;
    private final int MAX_POINTS = 50;

    @Override
    public Boolean validate(Integer limitPoints) {
        return (limitPoints > MIN_POINTS && limitPoints < MAX_POINTS);
    }
}

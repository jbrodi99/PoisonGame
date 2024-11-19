package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorGoldenCoinTypeTest {

    @Test
    public void validTypeGoldenCoinTest(){
        IValidator<ICard> validatorType = new ValidatorGoldenCoin();
        ICard sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        ICard goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        ICard cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        ICard goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertFalse(validatorType.validate(goblet));
        assertTrue(validatorType.validate(cup));
        assertTrue(validatorType.validate(goldenCoin));
        assertFalse(validatorType.validate(sword));
    }
}
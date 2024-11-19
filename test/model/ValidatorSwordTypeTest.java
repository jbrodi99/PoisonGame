package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorSwordTypeTest {

    @Test
    public void validTypeSwordTest(){
        IValidator<ICard> validatorType = new ValidatorSword();
        ICard sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        ICard goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        ICard cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        ICard goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertFalse(validatorType.validate(goblet));
        assertTrue(validatorType.validate(cup));
        assertFalse(validatorType.validate(goldenCoin));
        assertTrue(validatorType.validate(sword));
    }
}
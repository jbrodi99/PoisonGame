package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorGobletTypeTest {

    @Test
    public void validateTypeGobletTest() {
        IValidator<ICard> validatorType = new ValidatorGoblet();
        ICard sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        ICard goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        ICard cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        ICard goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertTrue(validatorType.validate(goblet));
        assertTrue(validatorType.validate(cup));
        assertFalse(validatorType.validate(goldenCoin));
        assertFalse(validatorType.validate(sword));
    }
}
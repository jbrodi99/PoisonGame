package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorGobletTypeTest {

    @Test
    public void validateTypeGobletTest() {
        IValidator<Card> validatorType = new ValidatorGoblet();
        Card sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        Card cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        Card goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertTrue(validatorType.validate(goblet));
        assertTrue(validatorType.validate(cup));
        assertFalse(validatorType.validate(goldenCoin));
        assertFalse(validatorType.validate(sword));
    }
}
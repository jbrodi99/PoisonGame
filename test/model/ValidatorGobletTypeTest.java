package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorGobletTypeTest {

    @Test
    public void validType() {
        IValidatorType validatorType = new ValidatorGobletType();
        Card sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        Card cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        Card goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertTrue(validatorType.validType(goblet));
        assertTrue(validatorType.validType(cup));
        assertFalse(validatorType.validType(goldenCoin));
        assertFalse(validatorType.validType(sword));
    }
}
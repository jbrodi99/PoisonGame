package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorSwordTypeTest {

    @Test
    public void validTypeSwordTest(){
        IValidatorType validatorType = new ValidatorSwordType();
        Card sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        Card cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        Card goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertFalse(validatorType.validType(goblet));
        assertTrue(validatorType.validType(cup));
        assertFalse(validatorType.validType(goldenCoin));
        assertTrue(validatorType.validType(sword));
    }
}
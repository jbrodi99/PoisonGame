package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorSwordTypeTest {

    @Test
    public void validTypeSwordTest(){
        IValidator<Card> validatorType = new ValidatorSword();
        Card sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        Card cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        Card goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertFalse(validatorType.validate(goblet));
        assertTrue(validatorType.validate(cup));
        assertFalse(validatorType.validate(goldenCoin));
        assertTrue(validatorType.validate(sword));
    }
}
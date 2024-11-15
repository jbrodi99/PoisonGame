package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorGoldenCoinTypeTest {

    @Test
    public void validTypeGoldenCoinTest(){
        IValidator<Card> validatorType = new ValidatorGoldenCoin();
        Card sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        Card cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        Card goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertFalse(validatorType.validate(goblet));
        assertTrue(validatorType.validate(cup));
        assertTrue(validatorType.validate(goldenCoin));
        assertFalse(validatorType.validate(sword));
    }
}
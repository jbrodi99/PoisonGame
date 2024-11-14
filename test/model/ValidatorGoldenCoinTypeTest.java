package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorGoldenCoinTypeTest {

    @Test
    public void validTypeGoldenCoinTest(){
        IValidatorType validatorType = new ValidatorGoldenCoinType();
        Card sword = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card goldenCoin = new Card(NUMBER.TWO, TYPECARD.GOLDEN_COIN);
        Card cup = new Card(NUMBER.TWO, TYPECARD.CUP);
        Card goblet = new Card(NUMBER.TWO, TYPECARD.GOBLET);
        assertFalse(validatorType.validType(goblet));
        assertTrue(validatorType.validType(cup));
        assertTrue(validatorType.validType(goldenCoin));
        assertFalse(validatorType.validType(sword));
    }
}
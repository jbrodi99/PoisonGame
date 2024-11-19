package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @org.junit.Test
    public void getValueTest() {
        ICard ICard = new Card(NUMBER.ONE,TYPECARD.SWORD);
        assert (NUMBER.ONE.equals(ICard.getValue()));
    }

    @org.junit.Test
    public void getTypeCard() {
        ICard ICard1 = new Card(NUMBER.ONE,TYPECARD.CUP);
        ICard ICard2 = new Card(NUMBER.ONE,TYPECARD.SWORD);
        ICard ICard3 = new Card(NUMBER.ONE,TYPECARD.GOBLET);
        ICard ICard4 = new Card(NUMBER.ONE,TYPECARD.GOLDEN_COIN);
        assert (TYPECARD.CUP.equals(ICard1.getTypeCard()));
        assert (TYPECARD.SWORD.equals(ICard2.getTypeCard()));
        assert (TYPECARD.GOBLET.equals(ICard3.getTypeCard()));
        assert (TYPECARD.GOLDEN_COIN.equals(ICard4.getTypeCard()));
    }

    @org.junit.Test
    public void isType() {
        ICard ICard1 = new Card(NUMBER.ONE,TYPECARD.CUP);
        assertTrue(ICard1.isType(TYPECARD.CUP));
    }

    @Test
    public void isMayorTenTest(){
        ICard ICard1 = new Card(NUMBER.TEN,TYPECARD.CUP);
        ICard ICard2 = new Card(NUMBER.TWELVE,TYPECARD.CUP);
        ICard ICard3 = new Card(NUMBER.ONE,TYPECARD.CUP);
        assertTrue(ICard1.isMayorTen());
        assertTrue(ICard2.isMayorTen());
        assertFalse(ICard3.isMayorTen());
    }
}
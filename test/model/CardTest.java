package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @org.junit.Test
    public void getValueTest() {
        Card card = new Card(NUMBER.ONE,TYPECARD.SWORD);
        assert (NUMBER.ONE.equals(card.getValue()));
    }

    @org.junit.Test
    public void getTypeCard() {
        Card card1 = new Card(NUMBER.ONE,TYPECARD.CUP);
        Card card2 = new Card(NUMBER.ONE,TYPECARD.SWORD);
        Card card3 = new Card(NUMBER.ONE,TYPECARD.GOBLET);
        Card card4 = new Card(NUMBER.ONE,TYPECARD.GOLDEN_COIN);
        assert (TYPECARD.CUP.equals(card1.getTypeCard()));
        assert (TYPECARD.SWORD.equals(card2.getTypeCard()));
        assert (TYPECARD.GOBLET.equals(card3.getTypeCard()));
        assert (TYPECARD.GOLDEN_COIN.equals(card4.getTypeCard()));
    }

    @org.junit.Test
    public void isType() {
        Card card1 = new Card(NUMBER.ONE,TYPECARD.CUP);
        assertTrue(card1.isType(TYPECARD.CUP));
    }

    @Test
    public void isMayorTenTest(){
        Card card1 = new Card(NUMBER.TEN,TYPECARD.CUP);
        Card card2 = new Card(NUMBER.TWELVE,TYPECARD.CUP);
        Card card3 = new Card(NUMBER.ONE,TYPECARD.CUP);
        assertTrue(card1.isMayorTen());
        assertTrue(card2.isMayorTen());
        assertFalse(card3.isMayorTen());
    }
}
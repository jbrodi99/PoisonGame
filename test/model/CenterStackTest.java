package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CenterStackTest {

    @Test
    public void isOverflowingTest() {
        CenterStack center = new CenterStack(TYPECARD.SWORD, new ValidatorSword());
        assertFalse(center.isOverflowing());
        center.addCard(new Card(NUMBER.NINE,TYPECARD.SWORD));   // count : 9
        assertFalse(center.isOverflowing());
        center.addCard(new Card(NUMBER.EIGHT,TYPECARD.SWORD));  // count : 17
        assertTrue(center.isOverflowing());
        center.removeTopCard();
        center.addCard(new Card(NUMBER.TEN,TYPECARD.SWORD));    // count : 9.5
        assertFalse(center.isOverflowing());
        center.addCard(new Card(NUMBER.ELEVEN,TYPECARD.SWORD)); // count : 10.0
        center.addCard(new Card(NUMBER.TWELVE,TYPECARD.SWORD)); // count : 10.5
        assertFalse(center.isOverflowing());
        center.addCard(new Card(NUMBER.TWO,TYPECARD.SWORD));    // count : 12.5
        assertFalse(center.isOverflowing());
        center.addCard(new Card(NUMBER.TEN,TYPECARD.SWORD));    // count : 13.0
        assertTrue(center.isOverflowing());
    }

    @Test
    public void countCardsTest() {
        CenterStack center = new CenterStack(TYPECARD.SWORD, new ValidatorSword());
        center.addCard(new Card(NUMBER.ELEVEN,TYPECARD.SWORD)); // 1
        center.addCard(new Card(NUMBER.TWELVE,TYPECARD.SWORD)); // 2
        assertTrue(center.countCards().equals(2));
        assertFalse(center.countCards().equals(1));
        assertFalse(center.countCards().equals(3));
    }

    @Test
    public void addCardTest() {
        CenterStack center = new CenterStack(TYPECARD.SWORD, new ValidatorSword());
        Card card = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(card);
        assertEquals(card,center.removeTopCard());
    }

    @Test
    public void isEmptyTest() {
        CenterStack center = new CenterStack(TYPECARD.SWORD, new ValidatorSword());
        assertTrue(center.isEmpty());
        Card card = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(card);
        assertFalse(center.isEmpty());
    }

    @Test
    public void removeTopCardTest() {
        CenterStack center = new CenterStack(TYPECARD.SWORD, new ValidatorSword());
        Card card0 = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(card0);
        Card card1 = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(card1);
        Card card2 = center.removeTopCard();
        Card card3 = center.removeTopCard();
        assertEquals(card0,card3);
        assertEquals(card1,card2);

    }
}
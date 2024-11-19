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
        ICard ICard = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(ICard);
        assertEquals(ICard,center.removeTopCard());
    }

    @Test
    public void isEmptyTest() {
        CenterStack center = new CenterStack(TYPECARD.SWORD, new ValidatorSword());
        assertTrue(center.isEmpty());
        ICard ICard = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(ICard);
        assertFalse(center.isEmpty());
    }

    @Test
    public void removeTopCardTest() {
        CenterStack center = new CenterStack(TYPECARD.SWORD, new ValidatorSword());
        ICard ICard0 = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(ICard0);
        ICard ICard1 = new Card(NUMBER.ELEVEN,TYPECARD.SWORD);
        center.addCard(ICard1);
        ICard ICard2 = center.removeTopCard();
        ICard ICard3 = center.removeTopCard();
        assertEquals(ICard0, ICard3);
        assertEquals(ICard1, ICard2);

    }
}
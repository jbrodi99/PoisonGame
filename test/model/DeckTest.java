package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void getInstanceTest() {
        IDeck deck = Deck.getInstance();
        IDeck otherDeck = Deck.getInstance();
        assertEquals(deck,otherDeck);
    }

    @Test
    public void shuffleDeckTest() {
        IDeck deck = Deck.getInstance();
        ICard ICard1 = deck.removeTopCard();
        deck.shuffleDeck();
        ICard ICard2 = deck.removeTopCard();
        assertNotEquals(ICard1, ICard2);
    }

    @Test
    public void countCardsTest() {
        IDeck deck = Deck.getInstance();
        deck.removeTopCard();
        assertTrue(47 == deck.countCards());
    }

    @Test
    public void removeTopCardTest() {
        IDeck deck = Deck.getInstance();
        ICard ICard = deck.removeTopCard();
        ICard otherICard = deck.removeTopCard();
        assertFalse(ICard.equals(otherICard));
    }

    @Test
    public void isEmptyTest() {
        IDeck deck = Deck.getInstance();
        assertFalse(deck.isEmpty());
        for (int i = 0; i < 48; i++) {
            deck.removeTopCard();
        }
        assertTrue(deck.isEmpty());
    }
}
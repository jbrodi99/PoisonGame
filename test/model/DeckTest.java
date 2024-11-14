package model;

import org.junit.Test;

import java.util.List;

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
        Card card1 = deck.removeTopCard();
        deck.shuffleDeck();
        Card card2 = deck.removeTopCard();
        assertNotEquals(card1,card2);
    }

    @Test
    public void cutDeckTest() {
        /*IDeck deck = Deck.getInstance();
        int middle = cards.size() / 2;
        Card card = cards.get(0);
        deck.cutDeck();
        assertEquals(card,cards.get(middle));*/
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
        Card card = deck.removeTopCard();
        Card otherCard = deck.removeTopCard();
        assertFalse(card.equals(otherCard));
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
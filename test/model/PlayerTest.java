package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void receiveCardTest() {
        IPlayer player = new Player(1,"juan",20);
        Card card = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card otherCard = new Card(NUMBER.ONE, TYPECARD.GOBLET);
        player.receiveCard(card);
        assertEquals(card,player.viewHand().get(0));
        assertNotEquals(otherCard,player.viewHand().get(0));
    }

    @Test
    public void viewHandTest() {
        IPlayer player = new Player(1,"juan",20);
        Card card = new Card(NUMBER.TWO, TYPECARD.SWORD);
        Card card1 = new Card(NUMBER.TWELVE, TYPECARD.SWORD);
        Card card2 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        Card card3 = new Card(NUMBER.FOUR, TYPECARD.SWORD);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        player.receiveCard(card);
        player.receiveCard(card1);
        player.receiveCard(card2);
        player.receiveCard(card3);
        List<Card> playerHand = player.viewHand();
        int i = 0;
        for(Card c : cards){
            assertEquals(c,playerHand.get(i++));
        }
    }

    @Test
    public void playCardTest() {
        IPlayer player = new Player(1,"juan",20);
        Card card = new Card(NUMBER.ONE, TYPECARD.GOBLET);
        player.receiveCard(card);
        assertEquals(card,player.playCard(0));
    }

    @Test
    public void countPoisonTest() {
        IPlayer player = new Player(1,"juan",20);
        Card card1 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        Card card2 = new Card(NUMBER.ONE, TYPECARD.CUP);
        Card card3 = new Card(NUMBER.ONE, TYPECARD.CUP);
        Card card4 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        CenterStack center = new CenterStack(TYPECARD.SWORD,new ValidatorSword());
        center.addCard(card1);
        center.addCard(card2);
        center.addCard(card3);
        center.addCard(card4);
        player.takeHeap(center);
        assertTrue(player.countPoison() == 2);
        assertFalse(player.countPoison().equals(3));
    }

    @Test
    public void receiveDamageTest() {
        IPlayer player = new Player(1,"juan",20);
        player.receiveDamage(10);
        assertEquals(10,player.getHealth());
        assertNotEquals(9,player.getHealth());
        player.receiveDamage(20);
        assertEquals(0,player.getHealth());
    }

    @Test
    public void takeHeapTest() {
        IPlayer player = new Player(1,"juan",20);
        Card card1 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        Card card2 = new Card(NUMBER.ONE, TYPECARD.CUP);
        Card card3 = new Card(NUMBER.ONE, TYPECARD.CUP);
        Card card4 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        CenterStack center = new CenterStack(TYPECARD.SWORD,new ValidatorSword());
        center.addCard(card1);
        center.addCard(card2);
        center.addCard(card3);
        center.addCard(card4);
        player.takeHeap(center);
        assertEquals(card4,player.getGraveyard().get(0));
        assertEquals(card3,player.getGraveyard().get(1));
        assertEquals(card2,player.getGraveyard().get(2));
        assertEquals(card1,player.getGraveyard().get(3));
    }

    @Test
    public void getIdTest() {
        IPlayer player = new Player(1,"juan",20);
        Integer one = 1;
        assertEquals(one,player.getId());
    }

    @Test
    public void getUserNameTest() {
        IPlayer player = new Player(1,"juan",20);
        assertEquals("juan",player.getUserName());
        assertNotEquals("carlos",player.getUserName());
    }

    @Test
    public void getHealthTest() {
        IPlayer player = new Player(1,"juan",20);
        assert (20 == player.getHealth());
    }

    @Test
    public void setTurnTest() {
        IPlayer player = new Player(1,"juan",20);
        Player player2 = new Player(2,"juancito",20);
        player.setTurn(true);
        assertTrue(player.getTurn());
        assertFalse(player2.getTurn());
    }

    @Test
    public void getTurnTest() {
        IPlayer player = new Player(1,"juan",20);
        player.setTurn(true);
        assertEquals(true,player.getTurn());
        assertNotEquals(false, player.getTurn());
        player.setTurn(false);
        assertEquals(false,player.getTurn());
    }
}
package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void receiveCardTest() {
        IPlayer player = new Player(1,"juan",20);
        ICard ICard = new Card(NUMBER.TWO, TYPECARD.SWORD);
        ICard otherICard = new Card(NUMBER.ONE, TYPECARD.GOBLET);
        player.receiveCard(ICard);
        assertEquals(ICard,player.viewHand().get(0));
        assertNotEquals(otherICard,player.viewHand().get(0));
    }

    @Test
    public void viewHandTest() {
        IPlayer player = new Player(1,"juan",20);
        ICard ICard = new Card(NUMBER.TWO, TYPECARD.SWORD);
        ICard ICard1 = new Card(NUMBER.TWELVE, TYPECARD.SWORD);
        ICard ICard2 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        ICard ICard3 = new Card(NUMBER.FOUR, TYPECARD.SWORD);
        List<ICard> ICards = new ArrayList<>();
        ICards.add(ICard);
        ICards.add(ICard1);
        ICards.add(ICard2);
        ICards.add(ICard3);
        player.receiveCard(ICard);
        player.receiveCard(ICard1);
        player.receiveCard(ICard2);
        player.receiveCard(ICard3);
        List<ICard> playerHand = player.viewHand();
        int i = 0;
        for(ICard c : ICards){
            assertEquals(c,playerHand.get(i++));
        }
    }

    @Test
    public void playCardTest() {
        IPlayer player = new Player(1,"juan",20);
        ICard ICard = new Card(NUMBER.ONE, TYPECARD.GOBLET);
        player.receiveCard(ICard);
        assertEquals(ICard,player.playCard(0));
    }

    @Test
    public void countPoisonTest() {
        IPlayer player = new Player(1,"juan",20);
        ICard ICard1 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        ICard ICard2 = new Card(NUMBER.ONE, TYPECARD.CUP);
        ICard ICard3 = new Card(NUMBER.ONE, TYPECARD.CUP);
        ICard ICard4 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        CenterStack center = new CenterStack(TYPECARD.SWORD,new ValidatorSword());
        center.addCard(ICard1);
        center.addCard(ICard2);
        center.addCard(ICard3);
        center.addCard(ICard4);
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
        ICard ICard1 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        ICard ICard2 = new Card(NUMBER.ONE, TYPECARD.CUP);
        ICard ICard3 = new Card(NUMBER.ONE, TYPECARD.CUP);
        ICard ICard4 = new Card(NUMBER.ONE, TYPECARD.SWORD);
        CenterStack center = new CenterStack(TYPECARD.SWORD,new ValidatorSword());
        center.addCard(ICard1);
        center.addCard(ICard2);
        center.addCard(ICard3);
        center.addCard(ICard4);
        player.takeHeap(center);
        assertEquals(ICard4,player.getGraveyard().get(0));
        assertEquals(ICard3,player.getGraveyard().get(1));
        assertEquals(ICard2,player.getGraveyard().get(2));
        assertEquals(ICard1,player.getGraveyard().get(3));
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
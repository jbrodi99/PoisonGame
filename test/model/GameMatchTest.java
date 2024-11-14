package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameMatchTest {

    @Test
    public void getCurrentPlayerTest() {
    }

    @Test
    public void getAllPlayersTest() {
    }

    @Test
    public void getDeckTest() {
    }

    @Test
    public void getLogTest() {
    }

    @Test
    public void getRankingTest() {
    }

    @Test
    public void initGameTest() {
        IGame game = GameMatch.getInstance();
        game.initGame(20,4);
        assertEquals(STATUS.INITIALIZED,game.getStatus());
        assertEquals(TYPECARD.SWORD,game.getCenter(TYPECARD.SWORD).getTypecard());
        assertEquals(TYPECARD.GOBLET,game.getCenter(TYPECARD.GOBLET).getTypecard());
        assertEquals(TYPECARD.GOLDEN_COIN,game.getCenter(TYPECARD.GOLDEN_COIN).getTypecard());
    }

    @Test
    public void nextTurnTest() {
    }

    @Test
    public void nextRoundTest() {
    }

    @Test
    public void dealHandTest() {
        IGame game = GameMatch.getInstance();
        game.initGame(20,4);
        String name1 = "juancito";
        String name2 = "ana";
        String name3 = "pepe";
        String name4 = "luis";
        int id1,id2,id3,id4;
        id1 = game.getLog().signIn(name1);
        id2 = game.getLog().signIn(name2);
        id3 = game.getLog().signIn(name3);
        id4 = game.getLog().signIn(name4);
        game.addPlayer(name1,id1);
        game.addPlayer(name2,id2);
        game.addPlayer(name3,id3);
        game.addPlayer(name4,id4);
        game.dealHand();
        Integer count = game.getDeck().countCards();
        assertTrue(count.equals(32));
    }

    @Test
    public void whoStartTest() {
        IGame game = GameMatch.getInstance();
        game.initGame(20,4);
        String name1 = "juancito";
        String name2 = "ana";
        String name3 = "pepe";
        String name4 = "luis";
        int id1,id2,id3,id4;
        id1 = game.getLog().signIn(name1);
        id2 = game.getLog().signIn(name2);
        id3 = game.getLog().signIn(name3);
        id4 = game.getLog().signIn(name4);
        game.addPlayer(name1,id1);
        game.addPlayer(name2,id2);
        game.addPlayer(name3,id3);
        game.addPlayer(name4,id4);
        game.whoStart();
    }

    @Test
    public void checkPlayTest() {
    }

    @Test
    public void endGameTest() {
    }
}
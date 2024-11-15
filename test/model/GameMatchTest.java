package model;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class GameMatchTest {

    @Test
    public void getAllPlayersTest() {
        IGameMatch game = GameMatch.getInstance();
        game.initGame(20,4);
        String name1 = "juancito";
        String name2 = "ana";
        String name3 = "pepe";
        String name4 = "luis";
        game.connectPlayer(name1,1,20);
        game.connectPlayer(name2,5,20);
        game.connectPlayer(name3,2,20);
        game.connectPlayer(name4,3,20);
        List<IPlayer> players = game.getAllPlayers();
        assertTrue(players.get(0).getId() == 1);
        assertTrue(players.get(1).getId() == 5);
        assertTrue(players.get(2).getId() == 2);
        assertTrue(players.get(3).getId() == 3);
    }

    @Test
    public void initGameTest() {
        IGameMatch game = GameMatch.getInstance();
        game.initGame(20,4);
        assertEquals(STATUS.INITIALIZED,game.getStatus());
        assertEquals(TYPECARD.SWORD,game.getCenter(TYPECARD.SWORD).getTypecard());
        assertEquals(TYPECARD.GOBLET,game.getCenter(TYPECARD.GOBLET).getTypecard());
        assertEquals(TYPECARD.GOLDEN_COIN,game.getCenter(TYPECARD.GOLDEN_COIN).getTypecard());
    }

    @Test
    public void nextTurnTest() {
        IGameMatch game = GameMatch.getInstance();
        game.initGame(20,4);
        String name1 = "juancito";
        String name2 = "ana";
        String name3 = "pepe";
        String name4 = "luis";
        game.connectPlayer(name1,1,20);
        game.connectPlayer(name2,5,20);
        game.connectPlayer(name3,2,20);
        game.connectPlayer(name4,3,20);
        int who = game.whoStart();
        ILog log = Log.getInstance();

        if(who == 1){
            assertTrue(game.getCurrentPlayer().getId() == log.signIn(name1));

        } else if (who == 2) {
            assertTrue(game.getCurrentPlayer().getId() == log.signIn(name2));

        } else if (who == 3) {
            assertTrue(game.getCurrentPlayer().getId() == log.signIn(name3));

        }else {
            assertTrue(game.getCurrentPlayer().getId() == log.signIn(name4));
        }
    }

    @Test
    public void nextRoundTest() {
        //pendiente...
    }

    @Test
    public void dealHandTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IGameMatch game = GameMatch.getInstance();
        game.initGame(20,4);
        Method methodPrivate = GameMatch.class.getDeclaredMethod("dealHand");
        methodPrivate.setAccessible(true);
        String name1 = "juancito";
        String name2 = "ana";
        String name3 = "pepe";
        String name4 = "luis";
        game.connectPlayer(name1,1,20);
        game.connectPlayer(name2,5,20);
        game.connectPlayer(name3,2,20);
        game.connectPlayer(name4,3,20);
        methodPrivate.invoke(game);
        Integer count = game.getDeck().countCards();
        assertTrue(count.equals(32));
    }

    @Test
    public void whoStartTest() {
        IGameMatch game = GameMatch.getInstance();
        game.initGame(20,4);
        String name1 = "juancito";
        String name2 = "ana";
        String name3 = "pepe";
        String name4 = "luis";
        game.connectPlayer(name1,1,20);
        game.connectPlayer(name2,5,20);
        game.connectPlayer(name3,2,20);
        game.connectPlayer(name4,3,20);
        game.whoStart();
    }

}
package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogTest {

    @Test
    public void isPlayerTest() {
        ILog log = Log.getInstance();
        assertTrue(log.isPlayer("pepe"));
        assertFalse(log.isPlayer("mario"));
    }

    @Test
    public void signUpTest() {
        ILog log = Log.getInstance();
        Integer id = log.signUp("pepe");
        Integer id2 = log.signUp("ana");
        assertTrue(id.equals(2));
        assertTrue(id2.equals(5));
    }

    @Test
    public void signInTest() {
        IGame game = GameMatch.getInstance();
        ILog log = Log.getInstance();
        Integer id3 = log.signUp("lola");
        assertTrue(id3.equals(0));
        Integer id = log.signIn("lola");
        assertTrue(id.equals(0));
        Integer id5 = log.signUp("ricky");
        assertTrue(id5.equals(1));
        Integer id4 = log.signIn("ricky");
        assertTrue(id4.equals(1));
    }
}
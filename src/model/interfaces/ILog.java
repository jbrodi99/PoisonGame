package model.interfaces;

import model.exceptions.NonExistsPlayerException;
import model.exceptions.PlayerAlreadyExistsException;

public interface ILog {
    boolean isPlayer(String userName);
    boolean isConnect(String username);
    void signUp(String userName) throws PlayerAlreadyExistsException;
    int signIn(String userName) throws NonExistsPlayerException;
    void logOut(String username) throws NullPointerException;
}

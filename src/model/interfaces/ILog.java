package model.interfaces;

import model.exceptions.NonExistsPlayerException;
import model.exceptions.PlayerAlreadyExistsException;

public interface ILog {
    boolean isPlayer(String userName);
    void signUp(String userName) throws PlayerAlreadyExistsException;
    int signIn(String userName) throws NonExistsPlayerException;
}

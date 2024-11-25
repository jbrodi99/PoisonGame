package model.interfaces;

public interface ILog {
    boolean isPlayer(String userName);
    int signUp(String userName);
    int signIn(String userName);
}

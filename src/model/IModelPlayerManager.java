package model;

public interface IModelPlayerManager {
    void connectPlayer(String userName, int id, int health);
    void disconnectPlayer(int id);
    boolean isAllPlayersConnect();
}

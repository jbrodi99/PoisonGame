package model;

import java.util.List;

public interface IGameMatch {
    IPlayer getCurrentPlayer();
    IPlayer getPlayerByID(int id);
    List<IPlayer> getAllPlayers();
    IDeck getDeck();
    ICenterStack getCenter(TYPECARD type);
    STATUS getStatus();
    void initGame(int limitPoints, int numOfPlayers);
    void setStatusGame(STATUS statusGame);
    int getConnectedPlayers();
    void connectPlayer(String userName, int id, int health);
    void disconnectPlayer(int id);
    void nextTurn();
    void nextRound();
    boolean checkRound();
    int whoStart();
    boolean isAllPlayersConnect();
}

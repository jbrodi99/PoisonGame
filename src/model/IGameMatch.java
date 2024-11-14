package model;

import java.util.List;

public interface IGameMatch {
    IPlayer getCurrentPlayer();
    IPlayer getPlayerByID(int id);
    List<IPlayer> getAllPlayers();
    IDeck getDeck();
    ICenterStack getCenter(TYPECARD type);
    STATUS getStatus();
    void addPlayer(String userName,int id);
    void nextTurn();
    void nextRound();
    void dealHand();
}

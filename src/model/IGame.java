package model;

import java.util.List;

public interface IGame {
    IPlayer getCurrentPlayer();
    IPlayer getPlayerByID(int id);
    List<IPlayer> getAllPlayers();
    IDeck getDeck();
    ILog getLog();
    IRanking getRanking();
    ICenterStack getCenter(TYPECARD type);
    STATUS getStatus();
    void addPlayer(String userName,int id);
    void initGame(int limitPoints, int numOfPlayers);
    void nextTurn();
    void nextRound();
    void dealHand();
    void whoStart();
    void checkPlay();
    void endGame();
}


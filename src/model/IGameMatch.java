package model;

import java.util.Queue;

public interface IGameMatch extends IModelGameManagement,IModelPlayerManager, IModelRoundManager{
    IDeck getDeck();
    void setDeck(IDeck deck);
    ICenterStack getCenter(TYPECARD type);
    STATUS getStatus();
    void setStatusGame(STATUS statusGame);
    Queue<IPlayer> getQueueTurns();
    void setNumOfPlayers(int nop);
    int getNumOfPLayers();
    int getLimitPoints();
    void setLimitPoints(int limitPoints);
}

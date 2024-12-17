package model.interfaces;

import model.exceptions.GameCompleteException;

public interface IPlayerManager {
    void connectPlayer(IGameMatch gameMatch,String userName, int id) throws GameCompleteException;
    void disconnectPlayer(IGameMatch gameMatch,int id);
    IPlayer getPlayerByID(IGameMatch gameMatch,int id);
    boolean isAllPlayersConnect(IGameMatch gameMatch);
    String getNamePlayers();
}

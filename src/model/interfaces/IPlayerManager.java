package model.interfaces;

import model.exceptions.GameCompleteException;

public interface IPlayerManager {
    void connectPlayer(IGameMatch gameMatch,String userName, int id) throws GameCompleteException;
    void reconnectPlayer(IGameMatch gameMatch, IPlayer player);
    void disconnectPlayer(IGameMatch gameMatch,int id);
    void disconnectAllPlayers(IGameMatch gameMatch);
    IPlayer getPlayerByID(IGameMatch gameMatch,int id);
    boolean isAllPlayersConnect(IGameMatch gameMatch);
    String getNamePlayers(IGameMatch gameMatch);
}

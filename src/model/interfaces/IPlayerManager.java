package model.interfaces;

public interface IPlayerManager {
    void connectPlayer(IGameMatch gameMatch,String userName, int id);
    void disconnectPlayer(IGameMatch gameMatch,int id);
    IPlayer getPlayerByID(IGameMatch gameMatch,int id);
    boolean isAllPlayersConnect(IGameMatch gameMatch);
    String getNamePlayers();
}

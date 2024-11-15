package model;

public interface IGameManagement extends IModelGameManagement{
    int getConnectedPlayers();
    IPlayer getCurrentPlayer();
}

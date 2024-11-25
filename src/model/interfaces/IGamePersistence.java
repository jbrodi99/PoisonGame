package model.interfaces;

public interface IGamePersistence {
    void loadGame(IGameMatch gameMatch, IPlayerManager playerManager);
    void saveGame(IGameMatch gameMatch, IPlayerManager playerManager);
}

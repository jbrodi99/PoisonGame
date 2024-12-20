package model.interfaces;

import model.logic.SavedGame;

import java.util.List;

public interface IGamePersistence {
    List<IGameMatchStatusPublic> findGames(String username);
    IGameMatch loadGame(int gameID);
    void saveGame(IGameMatch gameMatch, String playerNames);
}

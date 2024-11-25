package model.logic;

import model.interfaces.IGameMatch;
import model.interfaces.IGamePersistence;
import model.interfaces.IPlayer;
import model.interfaces.IPlayerManager;
import utils.Serializador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GamePersistence implements IGamePersistence, Serializable {

    private Serializador serializador;
    private List<SavedGame> gameList = new ArrayList<>();

    @Override
    public void loadGame(IGameMatch gameMatch, IPlayerManager playerManager) {
        gameList.add(new SavedGame(gameMatch, playerManager.getNamePlayers()));
    }

    @Override
    public void saveGame(IGameMatch gameMatch, IPlayerManager playerManager) {

    }

}

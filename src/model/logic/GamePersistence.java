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

    private static IGamePersistence instance = null;
    private Serializador serializador = new Serializador("Games.dat");
    private List<SavedGame> gameList = new ArrayList<>();

    public static IGamePersistence getInstance() {
        if(instance == null) instance = new GamePersistence();
        return instance;
    }

    private GamePersistence(){
    }

    @Override
    public void loadGame(IGameMatch gameMatch, IPlayerManager playerManager) {
        gameList.add(new SavedGame(gameMatch, playerManager.getNamePlayers()));
    }

    @Override
    public void saveGame(IGameMatch gameMatch, IPlayerManager playerManager) {

    }

}

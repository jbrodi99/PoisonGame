package model.logic;

import model.exceptions.GameCompleteException;
import model.factorys.IPlayerFactory;
import model.interfaces.IGameMatch;
import model.interfaces.IPlayer;
import model.interfaces.IPlayerManager;

import java.io.Serializable;

public class PlayerManager implements IPlayerManager, Serializable {

    private static IPlayerManager instance = null;
    private final IPlayerFactory playerFactory;

    public static IPlayerManager getInstance(IPlayerFactory playerFactory) {
        if(instance == null)    instance = new PlayerManager(playerFactory);
        return instance;
    }

    private PlayerManager(IPlayerFactory playerFactory){
        this.playerFactory = playerFactory;
    }

    @Override
    public void connectPlayer(IGameMatch gameMatch, String userName, int id) throws GameCompleteException {
        if(gameMatch.getPlayerGroup().howManyPlayersAreOnline() >= gameMatch.getStatus().getNumOfPLayers()){
            throw new GameCompleteException("The game match is already complete. Please select another game.");
        }
        IPlayer player = playerFactory.createPlayer(userName,id, gameMatch.getStatus().getLimitPoints());
        gameMatch.getTurn().addPLayer(player);
        gameMatch.getPlayerGroup().addPlayer(player);
    }

    @Override
    public void disconnectPlayer(IGameMatch gameMatch,int id){
        IPlayer player = getPlayerByID(gameMatch,id);
        gameMatch.getTurn().removePlayer(player);
        gameMatch.getPlayerGroup().removePlayer(player);
    }

    @Override
    public IPlayer getPlayerByID(IGameMatch gameMatch,int id){
        for(IPlayer p : gameMatch.getPlayerGroup().getPlayers()){
            if(p.areYou(id)){
                return p;
            }
        }
        throw new RuntimeException("El jugador no se encuentra en la partida");
    }

    @Override
    public boolean isAllPlayersConnect(IGameMatch gameMatch) {
        return gameMatch.getStatus().getNumOfPLayers() == gameMatch.getPlayerGroup().howManyPlayersAreOnline();
    }

    @Override
    public String getNamePlayers() {
        return "";
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements IPlayerManager{

    private final IGameMatch gameMatch;

    public PlayerManager(IGameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    @Override
    public void connectPlayer(String userName, int id, int health) {
        if(gameMatch.getQueueTurns().size() < gameMatch.getNumOfPLayers()){
            gameMatch.getQueueTurns().offer(new Player(id,userName, health));
            //Notificar evento CONNECT_PLAYER
            //notificarObservadores(EVENT.CONNECT_PLAYER);
        }
    }

    @Override
    public void disconnectPlayer(int id) {
        IPlayer player = getPlayerByID(id);
        gameMatch.getQueueTurns().remove(player);
        //Notificar evento DISCONNECT_PLAYER
        //notificarObservadores(EVENT.DISCONNECT_PLAYER);
    }

    @Override
    public boolean isAllPlayersConnect() {
        return gameMatch.getNumOfPLayers() == gameMatch.getQueueTurns().size();
    }

    @Override
    public IPlayer getPlayerByID(int id) {
        for(IPlayer p : getAllPlayers()){
            if(p.hasID(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<IPlayer> getAllPlayers() {
        //chequear esto...
        return new ArrayList<>(gameMatch.getQueueTurns());
    }
}

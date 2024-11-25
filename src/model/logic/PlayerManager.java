package model.logic;

import model.interfaces.IGameMatch;
import model.interfaces.IPlayer;
import model.interfaces.IPlayerManager;

import java.io.Serializable;
import java.rmi.RemoteException;

public class PlayerManager implements IPlayerManager, Serializable {

    @Override
    public void connectPlayer(IGameMatch gameMatch, String userName, int id) {
        //modificar para usar jugadores conectados y no cola de turnos
        if(gameMatch.getQueueTurns().size() < gameMatch.getNumOfPLayers()){
            IPlayer player = new Player(id,userName, gameMatch.getLimitPoints());
            gameMatch.getPlayers().add(player);
            gameMatch.getQueueTurns().offer(player);
        }
        //TODO: Lanzar exception por exceder el limite de jugadores
    }

    @Override
    public void disconnectPlayer(IGameMatch gameMatch,int id) throws RemoteException {
        //modificar para usar jugadores conectados y no cola de turnos
        IPlayer player = getPlayerByID(gameMatch,id);
        gameMatch.getQueueTurns().remove(player);
        gameMatch.getPlayers().remove(player);
    }

    @Override
    public IPlayer getPlayerByID(IGameMatch gameMatch,int id) throws RemoteException {
        //modificar para usar jugadores conectados y no cola de turnos
        for(IPlayer p : gameMatch.getAllPlayers()){
            if(p.areYou(id)){
                return p;
            }
        }
        //TODO: manejar exception de jugador que no se encuentra en la partida
        return null;
    }

    @Override
    public boolean isAllPlayersConnect(IGameMatch gameMatch) {
        //modificar para usar jugadores conectados y no cola de turnos
        return gameMatch.getNumOfPLayers() == gameMatch.getPlayers().size();
    }


    @Override
    public String getNamePlayers() {
        return "";
    }



}

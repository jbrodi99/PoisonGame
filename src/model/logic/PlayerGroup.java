package model.logic;

import model.interfaces.IPlayer;
import model.interfaces.IPlayerGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerGroup implements IPlayerGroup, Serializable {

    private List<IPlayer> players;

    public PlayerGroup(){
        players = new ArrayList<>();
    }

    @Override
    public void addPlayer(IPlayer player) {
        players.add(player);
    }

    @Override
    public void removePlayer(IPlayer player) {
        players.remove(player);
    }

    @Override
    public List<IPlayer> getPlayers() {
        return List.copyOf(players);
    }

    @Override
    public boolean isPLayerConnect(IPlayer player) {
        return players.contains(player);
    }

    @Override
    public int howManyPlayersAreOnline() {
        return players.size();
    }
}

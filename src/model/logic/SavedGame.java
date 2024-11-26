package model.logic;

import model.interfaces.IGameMatch;

import java.io.Serializable;

public class SavedGame implements Serializable {

    //TODO: This class is used for link the gameMatch and players to saveGame with Serializador

    private IGameMatch gameMatch;
    private final String[] players = new String[4];
    private final String playerNames;
    private static int IDGame = 0;
    private int id;

    public SavedGame(IGameMatch gameMatch, String playerNames){
        this.playerNames = playerNames;
        this.gameMatch = gameMatch;
        id = SavedGame.IDGame++;
        completePlayers();
    }

    private void completePlayers(){
        String[] arrayNames = playerNames.split(" ");
        int i = 0;
        for(String p : arrayNames){
            if (i < players.length) {
                players[i++] = p;
            } else {
                break;
            }
        }
    }

    public String[] getPlayers() {
        return players;
    }

    public IGameMatch getGameMatch() {
        return gameMatch;
    }
}

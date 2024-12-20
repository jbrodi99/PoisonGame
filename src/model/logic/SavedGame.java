package model.logic;

import model.interfaces.IGameMatch;


import java.io.Serializable;

public class SavedGame implements Serializable {

    private IGameMatch gameMatch;
    private final String[] players = {" "," "," "," "};
    private final String playerNames;

    public SavedGame(IGameMatch gameMatch, String playerNames){
        this.playerNames = playerNames;
        this.gameMatch = gameMatch;
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

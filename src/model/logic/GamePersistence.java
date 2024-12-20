package model.logic;

import model.interfaces.IGameMatch;
import model.interfaces.IGameMatchStatusPublic;
import model.interfaces.IGamePersistence;
import utils.Serializador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamePersistence implements IGamePersistence, Serializable {

    private static IGamePersistence instance = null;
    private Serializador serializador = new Serializador("out/artifacts/Veneno_jar/data/Games.dat");
    private List<SavedGame> gameList;

    public static IGamePersistence getInstance() {
        if(instance == null) instance = new GamePersistence();
        return instance;
    }

    private GamePersistence(){
        Object ogp = serializador.readFirstObject();
        gameList = (List<SavedGame>) ogp;
//        gameList = new ArrayList<>();
//        serializador.writeOneObject(gameList);
    }

    @Override
    public List<IGameMatchStatusPublic> findGames(String username) {
        List<SavedGame> games = new ArrayList<>();
        for (SavedGame savedGame : gameList) {
            if (findPlayer(savedGame,username)){
                games.add(savedGame);
            }
        }
        return games.stream().map(game -> game.getGameMatch().getStatus()).collect(Collectors.toList());
    }

    @Override
    public IGameMatch loadGame(int gameID) {
        for (SavedGame savedGame : gameList) {
            if (savedGame.getGameMatch().getStatus().getId() == gameID){
                return savedGame.getGameMatch();
            }
        }
        return null;
    }

    @Override
    public void saveGame(IGameMatch gameMatch, String playerNames) {
        gameList.removeIf(savedGame ->
                savedGame.getGameMatch().getStatus().getId() == gameMatch.getStatus().getId()
        );
        gameList.add(new SavedGame(gameMatch, playerNames));
        serializador.writeOneObject(gameList);
    }

    private boolean findPlayer(SavedGame game, String username){
        if (game.getPlayers() != null){
            for (String player : game.getPlayers()) {
                if (player.equals(username)){
                    return true;
                }
            }
        }
        return false;
    }
}

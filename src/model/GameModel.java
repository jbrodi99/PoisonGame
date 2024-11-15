package model;

public class GameModel implements IGameModel {

    private static IGameModel instance = null;
    private ILog log;
    private IRanking ranking;
    private IGameMatch gameMatch;

    private GameModel(){
        //instanciar log, ranking y partida, pero creo que se deberían recuperar con objetos serializados, chequear...
        log = Log.getInstance();
        ranking = Ranking.getInstance();
        gameMatch = GameMatch.getInstance();
    }

    public static IGameModel getInstance(){
        if(instance == null)   instance = new GameModel();
        return instance;
    }

    @Override
    public ILog getLog() {
        return log;
    }

    @Override
    public IRanking getRanking() {
        return ranking;
    }

    @Override
    public IGameMatch getGameMatch() {
        return gameMatch;
    }


    @Override
    public void startGame() {
        getGameMatch().startGame();
    }

    @Override
    public void endGame() {
        getGameMatch().endGame();
    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) {
        getGameMatch().initGame(limitPoints,numOfPlayers);
    }

    @Override
    public void connectPlayer(String userName, int id, int health) {
        getGameMatch().connectPlayer(userName,id,health);
    }

    @Override
    public void disconnectPlayer(int id) {
        getGameMatch().disconnectPlayer(id);
    }

    @Override
    public boolean isAllPlayersConnect() {
        return getGameMatch().isAllPlayersConnect();
    }

    @Override
    public void nextTurn() {
        getGameMatch().nextTurn();
    }

    @Override
    public void nextRound() {
        getGameMatch().nextRound();
    }


}

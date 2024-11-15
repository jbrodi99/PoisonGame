package model;

public class GameModel implements IGameModel {

    private static IGameModel instance = null;
    private ILog log;
    private IRanking ranking;
    private IGameMatch gameMatch;

    private GameModel(){
        //instanciar log, ranking y partida, pero creo que se deberían recuperar con objetos serializados
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
        //comienza cuando todos los jugadores estan conectados
        if(gameMatch.isAllPlayersConnect()){
            gameMatch.whoStart();
            gameMatch.setStatusGame(STATUS.RUNNING);
            //event
            //Notificar evento ALL_PLAYERS_CONNECT
            //notificarObservadores(EVENT.ALL_PLAYERS_CONNECT);
        }
    }

    @Override
    public void loadGame() {
        //cargar partida
        gameMatch.setStatusGame(STATUS.RUNNING);
    }

    @Override
    public void saveGame() {
        //guardar partida
        gameMatch.setStatusGame(STATUS.STOPED);
    }

    @Override
    public void endGame() {
        //gameMatch.setStatusGame(STATUS.END);
    }
}

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
    public void initGame(int limitPoints, int numOfPlayers) {

    }

    @Override
    public void startGame() {
        //comienza cuando todos los jugadores estan conectados
    }


    @Override
    public void loadGame() {

    }

    @Override
    public void saveGame() {

    }

    @Override
    public void checkPlay() {

    }

    @Override
    public void endGame() {

    }
//    @Override
//    public void initGame(int limitPoints, int numOfPlayers) {
//        if(getStatus().compareTo(STATUS.NOT_INIT) == 0){
//            this.statusGame = STATUS.INITIALIZED;
//            this.stacks.add(new CenterStack(TYPECARD.SWORD,new ValidatorSwordType()));
//            this.stacks.add(new CenterStack(TYPECARD.GOBLET,new ValidatorGobletType()));
//            this.stacks.add(new CenterStack(TYPECARD.GOLDEN_COIN,new ValidatorGoldenCoinType()));
//            this.limitPoints = limitPoints;
//            this.numOfPlayers = numOfPlayers;
//            this.whoStart();
//            return;
//        }
//        return;
//    }
}

package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.random.RandomGenerator;

public class GameMatch implements IGame{

    private static IGame instance = null;
    private STATUS statusGame = STATUS.NOT_INIT;
    //private List<IPlayer> players;
    private IDeck deck;
    private List<ICenterStack> stacks;
    private ILog log;
    private IRanking ranking;
    private Queue<IPlayer> queueTurns;
    private int limitPoints;
    private int currentTurn;
    private int numOfPlayers;

    public static IGame getInstance(){
        if(instance == null){
            instance = new GameMatch();
        }
        return instance;
    }

    private GameMatch(){
        //this.players = new ArrayList<>();
        this.deck = Deck.getInstance();
        this.log = Log.getInstance();
        //this.ranking = Ranking.getInstance();
        this.stacks = new ArrayList<>();
        this.queueTurns = new LinkedList<>();
    }

    @Override
    public IPlayer getPlayerByID(int id) {
        for(IPlayer p : getAllPlayers()){
            if(p.getId().compareTo(id) == 0){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<IPlayer> getAllPlayers() {
        return new ArrayList<>(this.queueTurns);
    }

    @Override
    public IDeck getDeck() {
        return this.deck;
    }

    @Override
    public ILog getLog() {
        return this.log;
    }

    @Override
    public IRanking getRanking() {
        return this.ranking;
    }

    @Override
    public ICenterStack getCenter(TYPECARD type) {
        return this.stacks.get(type.ordinal());
    }

    @Override
    public STATUS getStatus() {
        return this.statusGame;
    }

    @Override
    public void addPlayer(String userName, int id) {
        queueTurns.offer(new Player(id,userName));
    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) {
        if(getStatus().compareTo(STATUS.NOT_INIT) == 0){
            this.statusGame = STATUS.INITIALIZED;
            this.stacks.add(new CenterStack(TYPECARD.SWORD,new ValidatorSwordType()));
            this.stacks.add(new CenterStack(TYPECARD.GOBLET,new ValidatorGobletType()));
            this.stacks.add(new CenterStack(TYPECARD.GOLDEN_COIN,new ValidatorGoldenCoinType()));
            this.limitPoints = limitPoints;
            this.numOfPlayers = numOfPlayers;
            this.whoStart();
            return;
        }
        return;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return queueTurns.peek();
    }

    @Override
    public void nextTurn() {
        queueTurns.offer(queueTurns.poll());
        //notify next turn
    }

    @Override
    public void nextRound() {

    }

    @Override
    public void dealHand() {
        for (int i = 0; i < numOfPlayers; i++) {
            for (IPlayer p : queueTurns){
                p.receiveCard(getDeck().removeTopCard());
            }
        }
    }

    @Override
    public void whoStart() {
        this.currentTurn =(int) (Math.random() * (numOfPlayers - 1 + 1)) + 1;
    }

    @Override
    public void checkPlay() {

    }

    @Override
    public void endGame() {

    }
}

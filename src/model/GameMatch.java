package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameMatch implements IGameMatch{

    private static IGameMatch instance = null;
    private STATUS statusGame;
    private IDeck deck;
    private List<ICenterStack> stacks;
    private Queue<IPlayer> queueTurns;
    private int limitPoints;
    private int numOfPlayers;
    private int rounds;
    private int movesPerRound;
    private int turnsPlayed = 0;
    private final int MOVES_PER_TURN = 4;
    private final int MIN_POINTS = 0;
    private final int MAX_POINTS = 50;
    private final int MIX_PLAYERS = 2;
    private final int MAX_PLAYERS = 4;

    public static IGameMatch getInstance(){
        if(instance == null){
            instance = new GameMatch();
        }
        return instance;
    }

    private GameMatch(){
        this.stacks = new ArrayList<>();
        this.queueTurns = new LinkedList<>();
        this.stacks.add(new CenterStack(TYPECARD.SWORD,new ValidatorSwordType()));
        this.stacks.add(new CenterStack(TYPECARD.GOBLET,new ValidatorGobletType()));
        this.stacks.add(new CenterStack(TYPECARD.GOLDEN_COIN,new ValidatorGoldenCoinType()));
        this.statusGame = STATUS.NOT_INIT;
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
        return new ArrayList<>(this.queueTurns);
    }

    @Override
    public IDeck getDeck() {
        return this.deck;
    }

    @Override
    public ICenterStack getCenter(TYPECARD type) {
        return this.stacks.get(type.ordinal());
    }

    @Override
    public STATUS getStatus() {
        return statusGame;
    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) {
        if(getStatus() == STATUS.NOT_INIT){
            if(validLimitPoints(limitPoints)){
                //sino lanzar exception
                this.limitPoints = limitPoints;
            }
            if(validNumOfPlayers(numOfPlayers)){
                //sino lanzar exception
                this.numOfPlayers = numOfPlayers;
            }
            this.deck = Deck.getInstance();
            setTurnsPerRound();
            setRounds();
            setStatusGame(STATUS.INITIALIZED);
        }
    }

    @Override
    public int getConnectedPlayers() {
        return queueTurns.size();
    }

    @Override
    public void connectPlayer(String userName, int id, int health) {
        if(queueTurns.size() < numOfPlayers){
            queueTurns.offer(new Player(id,userName, health));
            //Notificar evento CONNECT_PLAYER
            //notificarObservadores(EVENT.CONNECT_PLAYER);
        }
    }

    @Override
    public void disconnectPlayer(int id) {
        IPlayer player = getPlayerByID(id);
        queueTurns.remove(player);
        //Notificar evento DISCONNECT_PLAYER
        //notificarObservadores(EVENT.DISCONNECT_PLAYER);
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return queueTurns.peek();
    }

    @Override
    public void nextTurn() {
        queueTurns.offer(queueTurns.poll());
        turnsPlayed++;
        //notificar Ronda jugada
        //notificarObservadores(EVENT.NEXT_TURN);
        /*Terminar de definir que acciones realizar desde el juego cuando se juega un turno*/
    }

    @Override
    public void nextRound() {
        turnsPlayed = 0;
        rounds--;
        dealHand();
        //notificar siguiente Ronda
        //notificarObservadores(EVENT.NEXT_ROUND);
    }

    @Override
    public boolean checkRound() {
        return turnsPlayed == movesPerRound;
    }

    @Override
    public boolean isAllPlayersConnect() {
        return numOfPlayers == queueTurns.size();
    }

    public int whoStart() {
        int firstTurn = 0;
        if(numOfPlayers > 0){
            firstTurn = (int) (Math.random() * (numOfPlayers - 1 + 1)) + 1;
            for (int i = 0; i < firstTurn - 1; i++) {
                queueTurns.offer(queueTurns.poll());
            }
        }
        return firstTurn;
    }

    public void setStatusGame(STATUS newStatus){
        this.statusGame = newStatus;
    }

    /*Private Methods of GameMatch...*/

    private void setRounds(){
        if(numOfPlayers > 0){
            this.rounds =  NUMBER.values().length / numOfPlayers;   // 6 or 4 or 3
        }
    }

    private void setTurnsPerRound(){
        if(numOfPlayers > 0){
            this.movesPerRound = MOVES_PER_TURN * numOfPlayers;
        }
    }

    private void dealHand() {
        for (int i = 0; i < numOfPlayers; i++) {
            for (IPlayer p : queueTurns){
                p.receiveCard(getDeck().removeTopCard());
            }
        }
    }

    private boolean validLimitPoints(int limitPoints){
        return (limitPoints > MIN_POINTS && limitPoints < MAX_POINTS);
    }

    private boolean validNumOfPlayers(int numOfPlayers){
        return (numOfPlayers >= MIX_PLAYERS && numOfPlayers <= MAX_PLAYERS);
    }
}

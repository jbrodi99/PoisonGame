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
    private int currentTurn;
    private int rounds;
    private int movesPerRound;
    private int turnsPlayed = 0;
    private final int MOVES_PER_TURN = 4;

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
    public void addPlayer(String userName, int id) {
        if(queueTurns.size() < numOfPlayers){
            queueTurns.offer(new Player(id,userName));
        }
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

    /*Private Methods of GameMatch...*/

    private void whoStart() {
        if(numOfPlayers > 0){
            this.currentTurn =(int) (Math.random() * (numOfPlayers - 1 + 1)) + 1;
        }
    }

    private void setStatusGame(STATUS newStatus){
        this.statusGame = newStatus;
    }

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
}

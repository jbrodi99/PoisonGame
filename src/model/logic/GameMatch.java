package model.logic;

import model.enums.NUMBER;
import model.enums.TYPECARD;
import model.exceptions.InvalidLimitPointsException;
import model.exceptions.InvalidNumOfPlayerException;
import model.exceptions.LostCardException;
import model.interfaces.*;
import model.validator.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameMatch implements IGameMatch, Serializable {

    private static IGameMatch instance = null;
    private IDeck deck;
    private final List<ICenterStack> stacks;
    private final List<IPlayer> playersConnected;
    private final Queue<IPlayer> queueTurns;
    private final IValidator<Integer> validatorPoints;
    private final IValidator<Integer> validatorPlayerCapacity;
    private int numOfPlayers;
    private int limitPoints;
    private int rounds;
    private int roundsPlayed = 1;
    private int movesPerRound;
    private int turnsPlayed = 0;

    public static IGameMatch getInstance(){
        if(instance == null){
            instance = new GameMatch();
        }
        return instance;
    }

    private GameMatch(){
        this.stacks = new ArrayList<>();
        this.playersConnected = new ArrayList<>();
        this.queueTurns = new LinkedList<>();
        this.stacks.add(new CenterStack(TYPECARD.SWORD));
        this.stacks.add(new CenterStack(TYPECARD.GOBLET));
        this.stacks.add(new CenterStack(TYPECARD.GOLDEN_COIN));
        this.validatorPoints = new ValidatorLimitPoints();
        this.validatorPlayerCapacity = new ValidatorPlayerCapacity();
    }

    @Override
    public ICenterStack getCenter(int pos) {
        return stacks.get(pos);
    }

    @Override
    public Queue<IPlayer> getQueueTurns() {
        return queueTurns;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return playersConnected;
    }

    @Override
    public int getNumOfPLayers() {
        return this.numOfPlayers;
    }

    public int getLimitPoints() {
        return limitPoints;
    }

    public void countTurn(){
        turnsPlayed++;
    }

    @Override
    public void countRound() {
        roundsPlayed++;
    }

    @Override
    public void resetTurns() {
        turnsPlayed = 0;
    }

    @Override
    public void resetRounds() {
        roundsPlayed = 1;
    }

    public int getMovesPerRound() {
        return movesPerRound;
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    @Override
    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public int getRounds() {
        return rounds;
    }

    @Override
    public void dealHand() throws LostCardException {
        deck.shuffleDeck();
        for(IPlayer p : queueTurns){
            p.receiveHand(deck.removeFourCards());
        }
    }

    @Override
    public void retrieveDeck() {
        for(IPlayer p : queueTurns){
            deck.addCards(p.emptyYourGraveyard());
        }
        for(ICenterStack center : stacks){
            deck.addCards(center.emptyStack());
        }
    }

    @Override
    public void estimateDamage() {
        for(IPlayer p : queueTurns){
            p.receiveDamage(p.countPoison());
            if(!p.isAlive() && queueTurns.size() > 1){
                queueTurns.remove(p);
            }
        }
    }

    @Override
    public void startGame() throws LostCardException {
        getCurrentPlayer().toggleTurn();    //Le doy el turno al primer jugador
        dealHand();
    }

    @Override
    public void endGame() {
        //ver que hacer, seguramente reiniciar el estado de todo el juego.
    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) throws InvalidLimitPointsException, InvalidNumOfPlayerException {
        if(!validatorPoints.validate(limitPoints))          throw new InvalidLimitPointsException("ERROR: valid range of points is... [" + ValidatorLimitPoints.MIN_POINTS + ", " + ValidatorLimitPoints.MAX_POINTS + " ]");
        if(!validatorPlayerCapacity.validate(numOfPlayers)) throw new InvalidNumOfPlayerException("ERROR: valid range of points is... [" + ValidatorPlayerCapacity.MIX_PLAYERS + " , " + ValidatorPlayerCapacity.MAX_PLAYERS +"]");
        this.numOfPlayers = numOfPlayers;
        this.limitPoints = limitPoints;
        this.deck = Deck.getInstance();
        this.movesPerRound = RoundManager.MOVES_PER_TURN * numOfPlayers;
        this.rounds = NUMBER.values().length / numOfPlayers;
    }

    @Override
    public int getConnectedPlayers() {
        return playersConnected.size();
    }

    @Override
    public int getPlayersAlive(){
        int alive = 0;
        for(IPlayer p : queueTurns){
            if(p.isAlive())   alive++;
        }
        return alive;
    }

    @Override
    public boolean hasWinner() {
        return queueTurns.size() == 1;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return queueTurns.peek();
    }

    @Override
    public List<IPlayer> getAllPlayers(){
        return new ArrayList<>(playersConnected);
    }

    @Override
    public List<ICenterStack> getAllCenters(){
        return new ArrayList<>(stacks);
    }
}

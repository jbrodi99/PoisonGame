package model;

import java.rmi.RemoteException;
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
    private final IGameManagement gameManagement;
    private final IPlayerManager playerManager;
    private final IRoundManager roundManager;
    private int numOfPlayers;
    private int limitPoints;

    public static IGameMatch getInstance(){
        if(instance == null){
            instance = new GameMatch();
        }
        return instance;
    }

    private GameMatch(){
        this.stacks = new ArrayList<>();
        this.queueTurns = new LinkedList<>();
        this.stacks.add(new CenterStack(TYPECARD.SWORD,new ValidatorSword()));
        this.stacks.add(new CenterStack(TYPECARD.GOBLET,new ValidatorGoblet()));
        this.stacks.add(new CenterStack(TYPECARD.GOLDEN_COIN,new ValidatorGoldenCoin()));
        this.gameManagement = new GameManagement(this);
        this.playerManager = new PlayerManager(this);
        this.roundManager = new RoundManager(this);
        this.statusGame = STATUS.NOT_INIT;
    }

    @Override
    public IDeck getDeck() {
        return this.deck;
    }

    @Override
    public void setDeck(IDeck deck) {
        this.deck = deck;
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
    public Queue<IPlayer> getQueueTurns() {
        return queueTurns;
    }

    @Override
    public void setNumOfPlayers(int nop) {
        this.numOfPlayers = nop;
    }

    @Override
    public int getNumOfPLayers() {
        return this.numOfPlayers;
    }

    public int getLimitPoints() {
        return limitPoints;
    }

    public void setLimitPoints(int limitPoints) {
        this.limitPoints = limitPoints;
    }

    public void setStatusGame(STATUS newStatus){
        this.statusGame = newStatus;
    }

    @Override
    public void startGame() throws RemoteException {
        gameManagement.startGame();
    }

    @Override
    public void endGame() throws RemoteException {
        gameManagement.endGame();
    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) throws RemoteException {
        gameManagement.initGame(limitPoints,numOfPlayers);
    }

    @Override
    public int getConnectedPlayers() throws RemoteException {
        return gameManagement.getConnectedPlayers();
    }

    @Override
    public IPlayer getCurrentPlayer() throws RemoteException {
        return gameManagement.getCurrentPlayer();
    }

    @Override
    public void connectPlayer(String userName, int id, int health) throws RemoteException {
        playerManager.connectPlayer(userName,id,health);
    }

    @Override
    public void disconnectPlayer(int id) throws RemoteException {
        playerManager.disconnectPlayer(id);
    }

    @Override
    public boolean isAllPlayersConnect() throws RemoteException {
        return playerManager.isAllPlayersConnect();
    }

    @Override
    public IPlayer getPlayerByID(int id) throws RemoteException {
        return playerManager.getPlayerByID(id);
    }

    @Override
    public List<IPlayer> getAllPlayers() throws RemoteException {
        return playerManager.getAllPlayers();
    }

    @Override
    public void nextTurn() throws RemoteException {
        roundManager.nextTurn();
    }

    @Override
    public void nextRound() throws RemoteException {
        roundManager.nextRound();
    }

    @Override
    public void whoStart() {
        roundManager.whoStart();
    }

    @Override
    public boolean checkRound() throws RemoteException {
        return roundManager.checkRound();
    }

    @Override
    public void setRounds() {
        roundManager.setRounds();
    }

    @Override
    public void setMovesPerRound() {
        roundManager.setMovesPerRound();
    }
}

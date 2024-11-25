package controller;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import model.enums.EVENT;
import model.exceptions.*;
import model.interfaces.IEventMap;
import model.interfaces.IGameModel;
import model.interfaces.IPlayer;
import model.logic.EventMap;
import view.IGameView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public class GameController implements IControladorRemoto {
    private IGameView view;
    private IGameModel model;
    private final IEventMap eventMap = new EventMap();
    private int playerID;

    public GameController(){
        registerEvents();
    }

    public void setView(IGameView view) {
        this.view = view;
    }

    public IGameView getView() {
        return view;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T model) throws RemoteException {
        this.model = (IGameModel) model;
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object eventDetected) throws RemoteException {
        eventMap.trigger((EVENT) eventDetected);
    }

    public int getPlayerID() {
        return playerID;
    }

    public void disconnectPlayer() {
        try {
            model.close(this, getPlayerID());
        }catch (RemoteException e){
            view.displayMessage("mistake has occurred.");
        }
    }

    public void playTurn(int indexCard,int indexCenter) {
        try {
            model.playTurn(indexCard, indexCenter);
        } catch (InvalidTypeCardException e) {
            view.displayMessage(e.getMessage());
        } catch (RemoteException e) {
            view.displayMessage("Error de red en play turn");
        } catch (LostCardException e) {
            view.displayMessage(e.getMessage());
        } catch (CardIndexOutOfBoundsException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public boolean gameExists(){
        boolean exists = false;
        try {
            exists =  model.isExists();
        } catch (RemoteException e) {
            view.displayMessage(e.getMessage());
        }
        return exists;
    }

    public boolean isPlayerConnect(){
        boolean isConnect = false;
        try {
            isConnect = model.isPlayerConnect(playerID);
        } catch (RemoteException e) {
            view.displayMessage(e.getMessage());
        }
        return isConnect;
    }

    public void loadGame(){}

    public void saveGame(){}

    public void startGame() {
        try {
            model.startGame();
        } catch (RemoteException e) {
            view.displayMessage("error de red");
        } catch (LostCardException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public void initGame(int limitPoints,int numOfPlayers) {
        try {
            model.initGame(limitPoints,numOfPlayers);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (InvalidLimitPointsException e) {
            view.displayMessage(e.getMessage());
        } catch (InvalidNumOfPlayerException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public void signIn(String userName){
        try {
            playerID = model.signIn(userName);
            model.connectPLayer(userName,playerID);
        } catch (RemoteException e) {
            view.displayMessage(e.getMessage());
        } catch (NonExistsPlayerException e) {
            view.displayMessage(e.getMessage());
        } catch (LostCardException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public void signUp(String userName){
        try {
            model.signUp(userName);
        } catch (PlayerAlreadyExistsException e) {
            view.displayMessage(e.getMessage());
        } catch (RemoteException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public void connectPlayer(String userName) {
        try {
            model.connectPLayer(userName, playerID);
            playerID = model.getCurrentPlayer().getId();
        } catch (RemoteException e) {
            view.displayMessage("error de red");
        } catch (LostCardException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public Map<String, Integer> getRanking() throws RemoteException {
        return model.getRanking().getScore();
    }

    private void registerEvents() {
        eventMap.register(EVENT.ALL_PLAYERS_CONNECT, this::allPlayerConnectHandler);
        eventMap.register(EVENT.DISCONNECT_PLAYER, this::disconnectPlayerHandler);
        eventMap.register(EVENT.CONNECT_PLAYER, this::connectPlayerHandler);
        eventMap.register(EVENT.PLAYER_PLAYED_CARD, this::playerPlayedCardHandler);
        eventMap.register(EVENT.NEXT_TURN, this::nextTurnHandler);
        eventMap.register(EVENT.NEXT_ROUND, this::nextRoundHandler);
        eventMap.register(EVENT.LOAD_GAME, this::loadGameHandler);
        eventMap.register(EVENT.SAVE_GAME, this::saveGameHandler);
        eventMap.register(EVENT.RESET_GAME, this::resetGameHandler);
        eventMap.register(EVENT.PLAYER_TAKE_HEAP, this::playerTakeHeapHandler);
        eventMap.register(EVENT.WINNER, this::winnerHandler);
    }

    /*
    * EventHandlers:
    * */

    private void allPlayerConnectHandler() {
        try {
            view.cleanBoard();
            IPlayer iam = model.getPlayerByID(playerID);
            IPlayer whoStart = model.getCurrentPlayer();
            if(whoStart.areYou(playerID)){
                view.displayActions();
            }else{
                view.hiddenActions(); //chequear si hace falta o dejo deshabilitado por defecto
            }
            view.displayBoard(new ArrayList<>(model.getAllCenters()), new ArrayList<>(model.getAllPlayers()));
            view.displayHand(new ArrayList<>(iam.viewHand()));
            view.displayGraveyard(new ArrayList<>(iam.getGraveyard()));
        } catch (RemoteException e) {
            view.displayMessage("Network Error (all players connect)");
        }
    }

    private void connectPlayerHandler() {
        try {
            view.cleanBoard();
            view.waitPlayer(model.getAllPlayers().size());
        } catch (RemoteException e) {
            view.displayMessage(e.getMessage());
        }
    }

    private void disconnectPlayerHandler() {
        // Código por implementar
    }

    private void playerPlayedCardHandler() {
        try {
            IPlayer player = model.getPlayerByID(playerID);
            view.cleanBoard();
            view.displayBoard(new ArrayList<>(model.getAllCenters()), new ArrayList<>(model.getAllPlayers()));
            view.displayHand(new ArrayList<>(player.viewHand()));
            view.displayGraveyard(new ArrayList<>(player.getGraveyard()));
        } catch (RemoteException e) {
            view.displayMessage("Network ERROR!!! (playerPlayedCard)");
        }
    }

    private void nextTurnHandler()  {
        try {
            IPlayer player = model.getPlayerByID(playerID);
            IPlayer currentPlayer = model.getCurrentPlayer();
            if (currentPlayer.areYou(playerID)) {
                view.displayActions();
            } else {
                view.hiddenActions();
            }
            view.cleanBoard();
            view.displayBoard(new ArrayList<>(model.getAllCenters()), new ArrayList<>(model.getAllPlayers()));
            view.displayHand(new ArrayList<>(player.viewHand()));
            view.displayGraveyard(new ArrayList<>(player.getGraveyard()));
        } catch (RemoteException e) {
            view.displayMessage("network ERROR (next turn)");
        }
    }

    private void nextRoundHandler() {
        try {
            IPlayer iam = model.getPlayerByID(playerID);
            view.cleanBoard();
            view.displayBoard(new ArrayList<>(model.getAllCenters()), new ArrayList<>(model.getAllPlayers()));
            view.displayHand(new ArrayList<>(iam.viewHand()));
            view.displayGraveyard(new ArrayList<>(iam.getGraveyard()));
        } catch (RemoteException e) {
            view.displayMessage("Network ERROR (next round)");
        }
    }

    private void loadGameHandler() {
        // Código por implementar
    }

    private void saveGameHandler() {
        // Código por implementar
    }

    private void resetGameHandler() {
        try {
            IPlayer iam = model.getPlayerByID(playerID);
            view.cleanBoard();
            view.displayBoard(new ArrayList<>(model.getAllCenters()), new ArrayList<>(model.getAllPlayers()));
            view.displayHand(new ArrayList<>(iam.viewHand()));
            view.displayGraveyard(new ArrayList<>(iam.getGraveyard()));
        } catch (RemoteException e) {
            view.displayMessage("Network error (reset game)");
        }
    }

    private void playerTakeHeapHandler() {
        try {
            IPlayer player = model.getPlayerByID(playerID);
            view.cleanBoard();
            view.displayBoard(new ArrayList<>(model.getAllCenters()), new ArrayList<>(model.getAllPlayers()));
            view.displayHand(new ArrayList<>(player.viewHand()));
            view.displayGraveyard(new ArrayList<>(player.getGraveyard()));
        } catch (RemoteException e) {
            view.displayMessage("Network ERROR when player take heap!!!");
        }
    }

    private void winnerHandler(){
        try {
            IPlayer winner = model.getCurrentPlayer();
            view.cleanBoard();
            view.hiddenActions();
            view.finishGame("El jugador " + winner.getUserName() + " es el ganador!!!");
        } catch (RemoteException e) {
            view.displayMessage("Network Error (winner)");
        }
    }
}

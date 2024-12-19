package controller;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import model.enums.EVENT;
import model.exceptions.*;
import model.interfaces.*;
import model.logic.Ranking;
import utils.*;
import view.interfaces.IGameView;
import view.interfaces.IView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController implements IControladorRemoto, IGameController {
    private IView popup;
    private IGameView view;
    private IGameModel model;
    private final IEventMap eventMap;
    private int playerID;
    private int currentGameId;

    public GameController(IEventMap eventMap, IView popup) {
        this.eventMap = eventMap;
        this.popup = popup;
        registerEvents();
    }

    @Override
    public IObservadorRemoto getObserver() {
        return this;
    }

    @Override
    public IView getPopup() {
        return popup;
    }

    public void setView(IGameView view) {
        this.view = view;
    }

    @Override
    public IGameView getView() {
        return view;
    }

    @Override
    public int getPlayerID() {
        return playerID;
    }

    @Override
    public int getCurrentGameID() {
        return currentGameId;
    }

    @Override
    public IGameModel getModel() {
        return model;
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T model) throws RemoteException {
        this.model = (IGameModel) model;
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object eventDetected) throws RemoteException {
        eventMap.trigger((IEventGame) eventDetected, currentGameId);
    }

    public void initGame(int limitPoints, int numOfPlayers, String gameName, String host) {
        try {
            currentGameId = model.initGame(limitPoints,numOfPlayers, gameName, host);
        } catch (RemoteException | InvalidLimitPointsException | InvalidNumOfPlayerException e) {
            popup.displayMessage(e.getMessage());
        }
    }

//    public boolean gameExists() {
//        boolean exists = false;
//        try {
//            exists = model.isExists(currentGameId);
//        } catch (Exception e) {
//            popup.displayMessage(e.getMessage());
//        }
//        return exists;
//    }

    public boolean signIn(String userName) {
        boolean status = false;
        try {
            playerID = model.signIn(userName);
            status = true;
        } catch (NonExistsPlayerException | RemoteException e) {
            popup.displayMessage(e.getMessage());
        }
        return status;
    }

    public void signUp(String userName) {
        try {
            model.signUp(userName);
        } catch (RemoteException | PlayerAlreadyExistsException  e) {
            popup.displayMessage(e.getMessage());
        }
    }

    public boolean connectPlayer(String username){
        boolean connect = false;
        try {
            model.connectPLayer(currentGameId,username,playerID);
            connect = true;
        } catch (RemoteException | LostCardException | GameCompleteException e) {
            popup.displayMessage(e.getMessage());
            return connect;
        }
        return connect;
    }

    public boolean connectPlayer(int gameID,String username){
        boolean connect = false;
        try {
            currentGameId = gameID;
            model.connectPLayer(gameID,username,playerID);
            connect = true;
        } catch (RemoteException | LostCardException | GameCompleteException e) {
            popup.displayMessage(e.getMessage());
            return connect;
        }
        return connect;
    }

    public void disconnectPlayer() {
        try {
            model.saveGame();
            model.close(this,currentGameId,playerID);
        } catch (RemoteException e) {
            popup.displayMessage(e.getMessage());
        }
    }

    public void playTurn(int indexCard, int indexCenter) {
        try {
            model.playTurn(currentGameId,indexCard,indexCenter);
        } catch (RemoteException | CardIndexOutOfBoundsException | LostCardException | InvalidTypeCardException e) {
            popup.displayMessage(e.getMessage());
        }
    }

    public List<IGameMatchStatusPublic> getMatches(){
        List<IGameMatchStatusPublic> matches = new ArrayList<>();
        try {
            matches =  model.getAllMatches();
            System.out.println(matches);
        } catch (RemoteException e) {
            popup.displayMessage(e.getMessage());
        }
        return matches;
    }

    public List<IPlayerPublic> getAllPlayers() {
        List<IPlayerPublic> players = new ArrayList<>();
        try {
            players = model.getAllPlayers(currentGameId);
        } catch (RemoteException e) {
            popup.displayMessage(e.getMessage());
        }
        return players;
    }

    public void loadGame() {
    }

    public void saveGame() {
    }

    public List<Map.Entry<String, Integer>> getRankingTable()  {
        List<Map.Entry<String, Integer>> table = new ArrayList<>();
        try {
            table =  model.getRankingTable();
        } catch (RemoteException e) {
            popup.displayMessage(e.getMessage());
        }
        return table;
    }

    public List<Ranking.SerializableEntry> getTopFive() {
        List<Ranking.SerializableEntry> top = new ArrayList<>();
        try {
            top =  model.getTopFive();
        } catch (RemoteException e) {
            popup.displayMessage(e.getMessage());
        }
        return top;
    }

    public void refresh() {
        SubController refreshController = new RefreshController(this);
        refreshController.run();

    }

    private void registerEvents() {
        eventMap.register(EVENT.ALL_PLAYERS_CONNECT, new AllPlayersConnectController(this));
        eventMap.register(EVENT.CONNECT_PLAYER, new ConnectPlayerController(this));
        eventMap.register(EVENT.PLAYER_PLAYED_CARD, new PlayerPlayedCardController(this));
        eventMap.register(EVENT.NEXT_TURN, new NextTurnController(this));
        eventMap.register(EVENT.NEXT_ROUND, new NextRoundController(this));
        eventMap.register(EVENT.RESET_GAME, new ResetGameController(this));
        eventMap.register(EVENT.PLAYER_TAKE_HEAP, new PlayerTakeHeapController(this));
        eventMap.register(EVENT.CLOSE_GAME, new CloseGameController(this));
        eventMap.register(EVENT.WINNER, new WinnerController(this));
    }
}

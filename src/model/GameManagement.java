package model;

import java.rmi.RemoteException;
import java.util.Queue;

public class GameManagement implements IGameManagement{

    private final IGameMatch gameMatch;
    private IValidator<Integer> validatorPoints;
    private IValidator<Integer> validatorPlayerCapacity;

    public GameManagement(IGameMatch gameMatch) {
        this.gameMatch = gameMatch;
        this.validatorPoints = new ValidatorLimitPoints();
        this.validatorPlayerCapacity = new ValidatorPlayerCapacity();
    }

    @Override
    public void startGame() {
        //gameMatch.whoStart();
    }

    @Override
    public void endGame() {

    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) {
        if(gameMatch.getStatus() == STATUS.NOT_INIT){
            if(!validatorPoints.validate(limitPoints))          throw new RuntimeException();   //TODO:crear mi exception
            if(!validatorPlayerCapacity.validate(numOfPlayers)) throw new RuntimeException();
            gameMatch.setNumOfPlayers(numOfPlayers);
            gameMatch.setLimitPoints(limitPoints);
            gameMatch.setDeck(Deck.getInstance());
            gameMatch.setMovesPerRound();
            gameMatch.setRounds();
            gameMatch.setStatusGame(STATUS.INITIALIZED);
        }
    }

    @Override
    public int getConnectedPlayers() {
        return gameMatch.getQueueTurns().size();
    }

    @Override
    public int getPlayersAlive() throws RemoteException {
        int alive = 0;
        Queue<IPlayer> queueTurns = gameMatch.getQueueTurns();
        for(IPlayer p : gameMatch.getQueueTurns()){
            if(p.getHealth() > 0)   alive++;
        }
        return alive;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return gameMatch.getQueueTurns().peek();
    }


}

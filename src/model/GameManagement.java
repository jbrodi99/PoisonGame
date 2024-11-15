package model;

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
            gameMatch.setStatusGame(STATUS.INITIALIZED);
        }
    }

    @Override
    public int getConnectedPlayers() {
        return gameMatch.getQueueTurns().size();
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return gameMatch.getQueueTurns().peek();
    }
}

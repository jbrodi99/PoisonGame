package model.factorys;

import model.exceptions.InvalidLimitPointsException;
import model.exceptions.InvalidNumOfPlayerException;
import model.interfaces.IDeck;
import model.interfaces.IGameMatch;
import model.interfaces.IGameMatchStatus;
import model.interfaces.IPlayerGroup;
import model.validator.IValidator;
import model.logic.GameMatch;
import model.validator.ValidatorLimitPoints;
import model.validator.ValidatorPlayerCapacity;

public class GameMatchFactory implements IGameMatchFactory{
    private final IValidator<Integer> validatorPoints = new ValidatorLimitPoints();
    private final IValidator<Integer> validatorPlayerCapacity = new ValidatorPlayerCapacity();

    @Override
    public IGameMatch createGameMatch(ICenterFactory centerFactory, ITurnFactory turnFactory, IRoundFactory roundFactory, IPlayerGroup playerGroup, IDeck deck, IGameMatchStatus status) throws InvalidLimitPointsException, InvalidNumOfPlayerException {
        if (!validatorPoints.validate(status.getLimitPoints())){
            throw new InvalidLimitPointsException("Invalid value of points. The valid range is : [ " + ValidatorLimitPoints.MIN_POINTS + ", " + ValidatorLimitPoints.MAX_POINTS + " ]");
        }
        if (!validatorPlayerCapacity.validate(status.getNumOfPLayers())){
            throw new InvalidNumOfPlayerException("Invalid value of number of players. The valid range is : [ " + ValidatorPlayerCapacity.MIX_PLAYERS + ", " + ValidatorPlayerCapacity.MAX_PLAYERS + " ]");
        }
        return new GameMatch(centerFactory,turnFactory, roundFactory, playerGroup, deck, status);
    }
}

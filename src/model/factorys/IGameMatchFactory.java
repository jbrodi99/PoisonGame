package model.factorys;

import model.exceptions.InvalidLimitPointsException;
import model.exceptions.InvalidNumOfPlayerException;
import model.interfaces.IDeck;
import model.interfaces.IGameMatch;
import model.interfaces.IPlayerGroup;

public interface IGameMatchFactory {
    IGameMatch createGameMatch(ICenterFactory centerFactory, ITurnFactory turnFactory, IRoundFactory roundFactory, IPlayerGroup playerGroup, IDeck deck, int limitPoints, int numOfPlayers) throws InvalidLimitPointsException, InvalidNumOfPlayerException;
}
package model.interfaces;

import model.exceptions.CardIndexOutOfBoundsException;
import model.exceptions.LostCardException;


public interface IRoundManager {
    void playTurn(IGameMatch gameMatch, int indexCard, int center) throws CardIndexOutOfBoundsException;
    void nextTurn(IGameMatch gameMatch);
    void nextRound(IGameMatch gameMatch) throws  LostCardException;
    void resetRound(IGameMatch gameMatch);
    boolean checkSanction(IGameMatch gameMatch, int indexCenter);
    void applySanction(IGameMatch gameMatch, int indexCenter);
    boolean checkValidMove(IGameMatch gameMatch, int indexCard, int indexCenter) throws  CardIndexOutOfBoundsException;
}

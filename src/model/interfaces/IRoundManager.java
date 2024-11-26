package model.interfaces;

import model.exceptions.CardIndexOutOfBoundsException;
import model.exceptions.LostCardException;

public interface IRoundManager {
    void playTurn(IGameMatch gameMatch, int indexCard, int center) throws CardIndexOutOfBoundsException;
    boolean nextTurn(IGameMatch gameMatch);
    boolean nextRound(IGameMatch gameMatch) throws  LostCardException;
    boolean checkAndApplySanction(IGameMatch gameMatch, int indexCenter);
    boolean checkMove(IGameMatch gameMatch, int indexCard, int indexCenter) throws  CardIndexOutOfBoundsException;
}

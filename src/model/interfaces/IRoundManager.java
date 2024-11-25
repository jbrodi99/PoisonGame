package model.interfaces;

import model.enums.TYPECARD;
import model.exceptions.CardIndexOutOfBoundsException;
import model.exceptions.LostCardException;

import java.rmi.RemoteException;

public interface IRoundManager {
//    TYPECARD playTurn(IGameMatch gameMatch, int indexCard) throws RemoteException;
    TYPECARD playTurn(IGameMatch gameMatch, int indexCard, int center) throws RemoteException, CardIndexOutOfBoundsException;
    boolean nextTurn(IGameMatch gameMatch) throws RemoteException;
    boolean nextRound(IGameMatch gameMatch) throws RemoteException, LostCardException;
//    boolean isAllTurnSPlayed(IGameMatch gameMatch) throws RemoteException;
    boolean checkAndApplySanction(IGameMatch gameMatch, int indexCenter) throws RemoteException;
    boolean checkMove(IGameMatch gameMatch, int indexCard, int indexCenter) throws RemoteException, CardIndexOutOfBoundsException;
}

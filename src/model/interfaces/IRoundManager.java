package model.interfaces;

import model.enums.TYPECARD;

import java.rmi.RemoteException;

public interface IRoundManager {
//    TYPECARD playTurn(IGameMatch gameMatch, int indexCard) throws RemoteException;
    TYPECARD playTurn(IGameMatch gameMatch, int indexCard, int center) throws RemoteException;
    boolean nextTurn(IGameMatch gameMatch) throws RemoteException;
    boolean nextRound(IGameMatch gameMatch) throws RemoteException;
//    boolean isAllTurnSPlayed(IGameMatch gameMatch) throws RemoteException;
    boolean checkAndApplySanction(IGameMatch gameMatch, int indexCenter) throws RemoteException;
    boolean checkMove(IGameMatch gameMatch, int indexCard, int indexCenter) throws RemoteException;
}

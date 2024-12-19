package model.interfaces;

import model.exceptions.CardIndexOutOfBoundsException;
import model.exceptions.InvalidTypeCardException;
import model.exceptions.LostCardException;

import java.rmi.RemoteException;

public interface Command {
    void execute() throws RemoteException, CardIndexOutOfBoundsException, InvalidTypeCardException, LostCardException;
}

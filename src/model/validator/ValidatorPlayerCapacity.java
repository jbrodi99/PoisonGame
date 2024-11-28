package model.validator;

import java.io.Serializable;

public class ValidatorPlayerCapacity implements IValidator<Integer>, Serializable {
    public final static int MIX_PLAYERS = 2;
    public final static int MAX_PLAYERS = 4;

    @Override
    public Boolean validate(Integer numOfPlayers) {
        return (numOfPlayers >= MIX_PLAYERS && numOfPlayers <= MAX_PLAYERS);
    }
}

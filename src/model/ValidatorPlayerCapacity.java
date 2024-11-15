package model;

public class ValidatorPlayerCapacity implements IValidator<Integer>{

    private final int MIX_PLAYERS = 2;
    private final int MAX_PLAYERS = 4;

    @Override
    public Boolean validate(Integer numOfPlayers) {
        return (numOfPlayers >= MIX_PLAYERS && numOfPlayers <= MAX_PLAYERS);
    }
}

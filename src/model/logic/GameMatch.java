package model.logic;

import model.factorys.ICenterFactory;
import model.factorys.IRoundFactory;
import model.factorys.ITurnFactory;
import model.interfaces.*;

import java.io.Serializable;
import java.util.List;


public class GameMatch implements IGameMatch, Serializable {

    private final IDeck deck;
    private final List<ICenterStack> stacks;
    private final IPlayerGroup playerGroup;
    private final ITurn turn;
    private final IRound round;
    private final IGameMatchStatus status;

    public GameMatch(ICenterFactory centerFactory, ITurnFactory turnFactory, IRoundFactory roundFactory, IPlayerGroup playerGroup, IDeck deck, IGameMatchStatus status){
        this.stacks = centerFactory.createCenter();
        this.playerGroup = playerGroup;
        this.deck = deck;
        this.status = status;
        this.turn = turnFactory.createTurn(status.getNumOfPLayers());
        this.round = roundFactory.createRound(status.getNumOfPLayers());
    }

    @Override
    public void startGame() {
        turn.getCurrentPlayer().toggleTurn();
        deck.dealHand(turn.getPlayersAlive());
    }

    @Override
    public void endGame() {
        //ver que hacer, seguramente reiniciar el estado de todo el juego.
    }

    @Override
    public IDeck getDeck() {
        return deck;
    }

    @Override
    public IGameMatchStatus getStatus() {
        return status;
    }

    @Override
    public IPlayerGroup getPlayerGroup() {
        return playerGroup;
    }

    @Override
    public ITurn getTurn() {
        return turn;
    }

    @Override
    public IRound getRound() {
        return round;
    }

    @Override
    public ICenterStack getCenter(int pos) {
        return stacks.get(pos);
    }

    @Override
    public List<ICenterStack> getAllCenters(){
        return stacks;
    }

    @Override
    public boolean hasWinner() {
        return turn.getPlayersAlive().size() == 1;
    }

    //Cheaquear si esta responsabilidad es del juego
    @Override
    public void estimateDamage() {
        for(IPlayer p : turn.getPlayersAlive()){
            p.receiveDamage(p.countPoison());
            if(!p.isAlive() && turn.getPlayersAlive().size() > 1){
                turn.removePlayer(p);
            }
        }
    }
}

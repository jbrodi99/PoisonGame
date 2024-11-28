package model.interfaces;

import java.util.List;

public interface IDeck {
    void shuffleDeck();
    void dealHand(List<IPlayer> players);
    void retrieveDeck(List<IPlayer> players, List<ICenterStack> centers);
}

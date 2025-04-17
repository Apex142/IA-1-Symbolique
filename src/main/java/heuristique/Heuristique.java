package heuristique;

import game.Grid;
import game.State;

public interface Heuristique {
    int evaluate(State state, Grid goalGrid);

}

package fr.mehdichadi.heuristique;

import fr.mehdichadi.game.State;

public interface Heuristique {
    int evaluate(State state, char[][] goalGrid);

}

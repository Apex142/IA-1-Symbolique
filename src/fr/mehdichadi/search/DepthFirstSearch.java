package fr.mehdichadi.search;

import fr.mehdichadi.game.State;

import java.util.Collection;
import java.util.Stack;

public class DepthFirstSearch extends SearchAlgorithm {

    @Override
    protected Collection<State> getOpenList() {
        return new Stack<>();
    }

    @Override
    protected State removeFromOpenList(Collection<State> openList) {
        return ((Stack<State>) openList).pop(); // LIFO : Dernier entré, premier sorti
    }
}

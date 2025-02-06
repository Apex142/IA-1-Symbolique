package fr.mehdichadi.search;

import fr.mehdichadi.game.State;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends SearchAlgorithm {

    @Override
    protected Collection<State> getOpenList() {
        return new LinkedList<>();
    }

    @Override
    protected State removeFromOpenList(Collection<State> openList) {
        return ((Queue<State>) openList).poll(); // FIFO : Premier entré, premier sorti
    }
}

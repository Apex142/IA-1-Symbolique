package search;

import game.State;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends SearchAlgorithm {

    private Queue<State> queue;

    @Override
    protected Collection<State> getOpenList() {
        queue = new LinkedList<>();
        return queue;
    }

    @Override
    protected State removeFromOpenList(Collection<State> openList) {
        return queue.poll(); // FIFO
    }
}

package search;

import game.State;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstSearch extends SearchAlgorithm {

    private Deque<State> stack;

    @Override
    protected Collection<State> getOpenList() {
        stack = new LinkedList<>();
        return stack;
    }

    @Override
    protected State removeFromOpenList(Collection<State> openList) {
        return stack.removeLast(); // LIFO
    }
}

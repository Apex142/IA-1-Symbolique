package fr.mehdichadi.search;

import fr.mehdichadi.game.State;
import fr.mehdichadi.heuristique.Heuristique;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Best-First Search algorithm implementation using a provided heuristic.
 * Selects the next state to explore based solely on the heuristic value h(n).
 */
public class BestFirstSearch extends SearchAlgorithm {

    private final Heuristique heuristic;
    private final char[][] goalGrid;

    /**
     * Constructor for BestFirstSearch.
     *
     * @param heuristic The heuristic function used to evaluate states.
     * @param goalGrid  The target goal configuration for the puzzle.
     */
    public BestFirstSearch(Heuristique heuristic, char[][] goalGrid) {
        this.heuristic = heuristic;
        this.goalGrid = goalGrid;
    }

    /**
     * Returns a priority queue sorted by heuristic value (lower is better).
     */
    @Override
    protected Collection<State> getOpenList() {
        return new PriorityQueue<>(Comparator.comparingInt(state -> heuristic.evaluate(state, goalGrid)));
    }

    /**
     * Retrieves and removes the state with the lowest heuristic value.
     */
    @Override
    protected State removeFromOpenList(Collection<State> openList) {
        return ((PriorityQueue<State>) openList).poll();
    }
}

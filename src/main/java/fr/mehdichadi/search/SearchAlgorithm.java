package fr.mehdichadi.search;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.Move;
import fr.mehdichadi.game.State;

import java.util.*;

public abstract class SearchAlgorithm {

    protected Collection<State> openList;
    protected Set<State> closedList;
    protected int nodesExplored = 0;

    public List<Move> search(State initialState) {
        openList = getOpenList();
        closedList = new HashSet<>();
        Map<State, State> cameFrom = new HashMap<>();
        Map<State, Move> moveMap = new HashMap<>();

        openList.add(initialState);
        cameFrom.put(initialState, null);

        while (!openList.isEmpty()) {
            State currentState = removeFromOpenList(openList);
            nodesExplored++;

            if (currentState.isGoalState()) {
                return reconstructPath(cameFrom, moveMap, currentState);
            }

            if (!closedList.contains(currentState)) {
                closedList.add(currentState);

                if (currentState instanceof Grid grid) {
                    for (Move move : getPossibleMoves(grid)) {
                        Grid newState = (Grid) move.apply(grid);
                        if (newState != null && !closedList.contains(newState) && !openList.contains(newState)) {
                            openList.add(newState);
                            cameFrom.put(newState, currentState);
                            moveMap.put(newState, move);
                        }
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    public int getNodesExplored() {
        return nodesExplored;
    }

    public Collection<State> getOpenListContent() {
        return openList;
    }

    public Set<State> getClosedListContent() {
        return closedList;
    }

    protected abstract Collection<State> getOpenList();
    protected abstract State removeFromOpenList(Collection<State> openList);

    private List<Move> getPossibleMoves(Grid grid) {
        List<Move> moves = new ArrayList<>();
        for (State successor : grid.getSuccessors()) {
            moves.add(new Move(grid, successor));
        }
        return moves;
    }

    private List<Move> reconstructPath(Map<State, State> cameFrom, Map<State, Move> moveMap, State currentState) {
        List<Move> path = new ArrayList<>();
        while (cameFrom.get(currentState) != null) {
            Move move = moveMap.get(currentState);
            path.add(0, move);
            currentState = cameFrom.get(currentState);
        }
        return path;
    }
}

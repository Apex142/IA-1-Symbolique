package search;

import game.Grid;
import game.Move;
import game.State;

import java.util.*;

public abstract class SearchAlgorithm {

    protected Collection<State> openList;
    protected Set<State> closedList;
    protected int nodesExplored = 0;
    protected int maxNodesExplored = Integer.MAX_VALUE; // ✅ Limite par défaut illimitée

    public void setMaxNodesExplored(int max) {
        this.maxNodesExplored = max;
    }

    public List<Move> search(State initialState, State goalState) {
        openList = getOpenList();
        closedList = new HashSet<>();
        Map<State, State> cameFrom = new HashMap<>();
        Map<State, Move> moveMap = new HashMap<>();

        openList.add(initialState);
        cameFrom.put(initialState, null);

        while (!openList.isEmpty()) {
            State currentState = removeFromOpenList(openList);
            nodesExplored++;

            // ✅ Arrêter si on atteint la limite autorisée
            if (nodesExplored >= maxNodesExplored) {
                return new ArrayList<>(); // ou return null si tu préfères
            }

            if (currentState.equals(goalState)) {
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

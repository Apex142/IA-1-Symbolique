package search.report;

import game.Grid;
import game.Move;
import game.State;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SearchReport {

    private final Grid initialState;
    private final Grid goalState;
    private final List<Move> solution;
    private final int nodesExplored;
    private final long durationMillis;
    private final Collection<State> openList;
    private final Set<State> closedList;

    public SearchReport(Grid initialState, Grid goalState, List<Move> solution, int nodesExplored,
                        long durationMillis, Collection<State> openList, Set<State> closedList) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.solution = solution;
        this.nodesExplored = nodesExplored;
        this.durationMillis = durationMillis;
        this.openList = openList;
        this.closedList = closedList;
    }

    public void display() {
        System.out.println("=== Search Report ===");
        System.out.println("Etat initial:\n" + initialState);
        System.out.println("Etat final:\n" + goalState);

        if (solution == null || solution.isEmpty()) {
            System.out.println("Aucune solution trouvé.");
        } else {
            System.out.println("Steps (" + solution.size() + "):");
            for (int i = 0; i < solution.size(); i++) {
                Move move = solution.get(i);
                System.out.println("Step " + (i + 1) + ":\n" + move.getNextState());
            }
            System.out.println("==================");
            System.out.println("Solution trouvé");
            System.out.println("Etapes : " + solution.size());
            System.out.println("==================");
        }

        System.out.println("Temps: " + durationMillis + " ms");
        System.out.println("==================");
        System.out.println("Liste FERMEE (" + closedList.size() + ")");
        System.out.println("\nListe OUVERTE (" + openList.size() + ")");
        System.out.println("==================");
    }


    public int getNodesExplored() {
        return nodesExplored;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    public Collection<State> getOpenList() {
        return openList;
    }

    public Set<State> getClosedList() {
        return closedList;
    }

    public List<Move> getSolution() {
        return solution;
    }
}
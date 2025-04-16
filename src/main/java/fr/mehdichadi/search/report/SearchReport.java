package fr.mehdichadi.search.report;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.Move;
import fr.mehdichadi.game.State;

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
        System.out.println("Initial State:\n" + initialState);
        System.out.println("Goal State:\n" + goalState);
        System.out.println("Steps (" + solution.size() + "):");
        for (int i = 0; i < solution.size(); i++) {
            Move move = solution.get(i);
            System.out.println("Step " + (i + 1) + ":\n" + move.getNextState());
        }
        System.out.println("\n✅ Solution found!");
        System.out.println("🔢 Steps: " + solution.size());
        System.out.println("🌳 Nodes explored: " + nodesExplored);
        System.out.println("⏱️ Duration: " + durationMillis + " ms");

        System.out.println("\n📂 Liste FERMÉE (" + closedList.size() + ")");

        System.out.println("\n📬 Liste OUVERTE (" + openList.size() + ")");
    }

    public int getNodesExplored() {
        return nodesExplored;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    public List<Move> getSolution() {
        return solution;
    }
}

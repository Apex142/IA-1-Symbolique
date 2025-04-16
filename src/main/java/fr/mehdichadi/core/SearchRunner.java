package fr.mehdichadi.core;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.Move;
import fr.mehdichadi.search.SearchAlgorithm;
import fr.mehdichadi.search.report.SearchReport;

import java.util.List;

public class SearchRunner {

    public SearchReport runSearch(SearchAlgorithm algorithm, Grid initialState) {
        long start = System.currentTimeMillis();

        List<Move> solution = algorithm.search(initialState);
        int exploredNodes = algorithm.getNodesExplored();
        long duration = System.currentTimeMillis() - start;

        System.out.println("solution: " + solution.size());

        return new SearchReport(
                initialState,
                initialState.getGoalGrid(),
                solution,
                exploredNodes,
                duration,
                algorithm.getOpenListContent(),
                algorithm.getClosedListContent()
        );
    }
}

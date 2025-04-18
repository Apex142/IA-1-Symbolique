package core;

import game.Grid;
import game.Move;
import search.report.SearchReport;
import search.SearchAlgorithm;

import java.util.List;

public class SearchRunner {

    private int maxNodesExplored = Integer.MAX_VALUE;

    public void setMaxNodesExplored(int max) {
        this.maxNodesExplored = max;
    }

    public SearchReport runSearch(SearchAlgorithm algorithm, Grid initialState, Grid finalState) {
        algorithm.setMaxNodesExplored(maxNodesExplored); // <-- ajout ici

        long start = System.currentTimeMillis();

        List<Move> solution = algorithm.search(initialState, finalState);
        int exploredNodes = algorithm.getNodesExplored();
        long duration = System.currentTimeMillis() - start;

        return new SearchReport(
                initialState,
                finalState,
                solution,
                exploredNodes,
                duration,
                algorithm.getOpenListContent(),
                algorithm.getClosedListContent()
        );
    }
}

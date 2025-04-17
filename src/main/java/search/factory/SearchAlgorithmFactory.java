package search.factory;

import game.Grid;
import heuristique.ManhattanPlusLinearConflict;
import heuristique.MisplacedTilesHeuristic;
import search.BreadthFirstSearch;
import search.DepthFirstSearch;
import search.SearchAlgorithm;
import search.SearchType;
import search.BestFirstSearch;
import heuristique.ManhattanDistance;
import heuristique.Heuristique;

public class SearchAlgorithmFactory {

    public static SearchAlgorithm getAlgorithm(SearchType type, Grid goalGrid) {
        Heuristique heuristic = new ManhattanDistance();

        return switch (type) {
            case DFS -> new DepthFirstSearch();
            case BFS -> new BreadthFirstSearch();
            case BEST_FIRST -> new BestFirstSearch(heuristic, goalGrid);
            case BEST_FIRST_MISPLACED -> new BestFirstSearch(new MisplacedTilesHeuristic(), goalGrid);
            case BEST_FIRST_LINEAR -> new BestFirstSearch(new ManhattanPlusLinearConflict(), goalGrid);

        };
    }

}

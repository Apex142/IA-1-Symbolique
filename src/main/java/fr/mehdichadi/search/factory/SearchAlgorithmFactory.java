package fr.mehdichadi.search.factory;

import fr.mehdichadi.search.BreadthFirstSearch;
import fr.mehdichadi.search.DepthFirstSearch;
import fr.mehdichadi.search.SearchAlgorithm;
import fr.mehdichadi.search.SearchType;

public class SearchAlgorithmFactory {

    public static SearchAlgorithm getAlgorithm(SearchType type) {
        return switch (type) {
            case DFS -> new DepthFirstSearch();
            case BFS -> new BreadthFirstSearch();
            case BEST_FIRST -> {
                // TODO: Implémenter et ajouter BestFirstSearch
                throw new UnsupportedOperationException("Best-First Search is not implemented yet.");
            }
        };
    }
}

package search;

/**
 * Enumération des types d'algorithmes de recherche disponibles.
 */
public enum SearchType {
    DFS,         // Depth-First Search
    BFS,         // Breadth-First Search
    BEST_FIRST,   // Best-First Search (avec heuristique)
    BEST_FIRST_MISPLACED,
    BEST_FIRST_LINEAR
}
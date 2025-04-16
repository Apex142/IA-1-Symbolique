package fr.mehdichadi.core;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.search.SearchAlgorithm;
import fr.mehdichadi.search.SearchType;
import fr.mehdichadi.search.factory.SearchAlgorithmFactory;
import fr.mehdichadi.search.report.SearchReport;

public class Main {
    public static void main(String[] args) {
        String filePath = "taquin_2x4b.grid";
        SearchType searchType = SearchType.DFS; // Choisir l'algorithme ici (DFS, BFS, BEST_FIRST)

        // Chargement de l'état initial
        GameLoader loader = new GameLoader();
        Grid initialState = loader.loadGame(filePath);

        // Choix et exécution de l'algorithme
        SearchAlgorithm algorithm = SearchAlgorithmFactory.getAlgorithm(searchType);
        SearchRunner runner = new SearchRunner();
        SearchReport report = runner.runSearch(algorithm, initialState);

        // Affichage du rapport de recherche
        report.display();
    }
}

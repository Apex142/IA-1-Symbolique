package core;

import game.Grid;
import search.SearchAlgorithm;
import search.SearchType;
import search.factory.SearchAlgorithmFactory;
import search.report.SearchReport;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Utilise un chemin absolu ou relatif vers le fichier .grid
        File file = new File("grids/taquin_2x4.grid"); // ou un autre chemin selon ton organisation
        SearchType searchType = SearchType.BEST_FIRST; // DFS, BFS, BEST_FIRST

        // Chargement de l'état initial et de la grille objectif
        GameLoader loader = new GameLoader();
        Grid initialState = loader.loadGame(file);
        Grid goalGrid = loader.getGoalGrid(); // Grille finale

        // Choix et exécution de l'algorithme
        SearchAlgorithm algorithm = SearchAlgorithmFactory.getAlgorithm(searchType, goalGrid);
        SearchRunner runner = new SearchRunner();
        SearchReport report = runner.runSearch(algorithm, initialState, goalGrid);

        // Affichage du rapport de recherche
        report.display();
    }
}

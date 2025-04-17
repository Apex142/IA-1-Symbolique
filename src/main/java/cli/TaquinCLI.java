package cli;

import core.GameLoader;
import core.SearchRunner;
import game.Grid;
import heuristique.ManhattanDistance;
import search.*;
import search.report.SearchReport;

import java.io.File;

public class TaquinCLI {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("❌ Usage: java TaquinCLI <algorithm> <path-to-grid-file>");
            System.err.println("🔹 Example: java TaquinCLI DFS /path/to/taquin.grid");
            System.exit(1);
        }

        String algoName = args[0].toUpperCase();
        File file = new File(args[1]);

        if (!file.exists()) {
            System.err.println("❌ Fichier introuvable : " + file.getAbsolutePath());
            System.exit(1);
        }

        try {
            // Chargement de la grille
            GameLoader loader = new GameLoader();
            Grid initial = loader.loadGame(file);
            Grid goal = loader.getGoalGrid();

            // Sélection de l'algorithme
            SearchAlgorithm algorithm = switch (algoName) {
                case "DFS" -> new DepthFirstSearch();
                case "BFS" -> new BreadthFirstSearch();
                case "BEST_FIRST" -> new BestFirstSearch(new ManhattanDistance(), goal);
                default -> throw new IllegalArgumentException("⚠️ Algorithme inconnu : " + algoName);
            };

            // Exécution
            System.out.println("▶️ Lancement de " + algoName + " sur " + file.getName());
            SearchRunner runner = new SearchRunner();
            SearchReport report = runner.runSearch(algorithm, initial, goal);

            // Résultat
            report.display();

        } catch (Exception e) {
            System.err.println("❌ Erreur durant l'exécution : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

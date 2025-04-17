package export;

import core.GameLoader;
import core.SearchRunner;
import game.Grid;
import heuristique.ManhattanDistance;
import heuristique.ManhattanPlusLinearConflict;
import heuristique.MisplacedTilesHeuristic;
import search.*;
import search.report.SearchReport;

import java.io.*;
import java.util.*;

public class PerformanceExporter {

    private static final List<SearchType> selectedAlgorithms = List.of(
            SearchType.DFS,
            SearchType.BFS,
            SearchType.BEST_FIRST,
            SearchType.BEST_FIRST_MISPLACED,
            SearchType.BEST_FIRST_LINEAR
    );

    private static final List<String> selectedGrids = List.of(
            "taquin_4x4b.grid"
    );

    public static void main(String[] args) {
        File output = new File("comparaison.csv");

        try (PrintWriter writer = new PrintWriter(output)) {
            writer.println("Fichier,Algorithme,Étapes,Nœuds Explorés,Durée(ms),Ouvert,Fermé");

            for (String gridFile : selectedGrids) {
                File file = new File("src/main/resources/grids/" + gridFile);
                if (!file.exists()) {
                    System.err.println("❌ Fichier introuvable : " + file.getAbsolutePath());
                    continue;
                }

                GameLoader loader = new GameLoader();
                Grid initial = loader.loadGame(file);
                Grid goal = loader.getGoalGrid();

                for (SearchType algoType : selectedAlgorithms) {
                    SearchAlgorithm algo = switch (algoType) {
                        case DFS -> new DepthFirstSearch();
                        case BFS -> new BreadthFirstSearch();
                        case BEST_FIRST -> new BestFirstSearch(new ManhattanDistance(), goal);
                        case BEST_FIRST_MISPLACED -> new BestFirstSearch(new MisplacedTilesHeuristic(), goal);
                        case BEST_FIRST_LINEAR -> new BestFirstSearch(new ManhattanPlusLinearConflict(), goal);
                    };

                    SearchRunner runner = new SearchRunner();
                    SearchReport report = runner.runSearch(algo, initial, goal);

                    int steps = report.getSolution() == null ? 0 : report.getSolution().size();

                    writer.printf("%s,%s,%d,%d,%d,%d,%d%n",
                            gridFile,
                            algoType,
                            steps,
                            report.getNodesExplored(),
                            report.getDurationMillis(),
                            report.getOpenList().size(),
                            report.getClosedList().size()
                    );
                    System.out.println("✅ Terminé : " + gridFile + " avec " + algoType);
                }
            }

            System.out.println("📄 Fichier CSV généré : " + output.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

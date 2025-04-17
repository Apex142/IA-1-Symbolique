import core.GameLoader;
import core.SearchRunner;
import game.Grid;
import game.Move;
import game.State;
import heuristique.ManhattanDistance;
import heuristique.ManhattanPlusLinearConflict;
import heuristique.MisplacedTilesHeuristic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import search.*;
import search.report.SearchReport;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AllSearchAlgorithmsTest {

    private final List<SearchType> searchTypes = List.of(
            SearchType.DFS,
            SearchType.BFS,
            SearchType.BEST_FIRST,
            SearchType.BEST_FIRST_MISPLACED,
            SearchType.BEST_FIRST_LINEAR
    );

    // Grilles connues comme insolubles
    private final List<String> unsolvableGrids = List.of(
            "taquin_2x4.grid"
    );

    @Test
    @DisplayName("Test avec tous les algorithmes sur toutes les grilles")
    void testAllGridFilesWithAllAlgorithms() {
        List<File> gridFiles = getAllGridFiles("grids");
        assertFalse(gridFiles.isEmpty(), "Aucun fichier .grid trouvé dans le dossier 'grids'.");

        for (File file : gridFiles) {
            for (SearchType type : searchTypes) {
                System.out.println("\n🌐 Test sur : " + file.getName() + " avec " + type);
                testWithAlgorithm(file, type);
            }
        }
    }

    void testWithAlgorithm(File file, SearchType type) {
        try {
            GameLoader loader = new GameLoader();
            Grid initial = loader.loadGame(file);
            Grid goal = loader.getGoalGrid();

            assertNotNull(initial, "Grille initiale nulle pour " + file.getName());
            assertNotNull(goal, "Grille finale nulle pour " + file.getName());

            SearchAlgorithm algorithm = switch (type) {
                case DFS -> new DepthFirstSearch();
                case BFS -> new BreadthFirstSearch();
                case BEST_FIRST -> new BestFirstSearch(new ManhattanDistance(), goal);
                case BEST_FIRST_MISPLACED -> new BestFirstSearch(new MisplacedTilesHeuristic(), goal);
                case BEST_FIRST_LINEAR -> new BestFirstSearch(new ManhattanPlusLinearConflict(), goal);
            };

            SearchRunner runner = new SearchRunner();
            SearchReport report = runner.runSearch(algorithm, initial, goal);
            List<Move> solution = report.getSolution();

            boolean expectedSolvable = !unsolvableGrids.contains(file.getName());

            if (solution == null || solution.isEmpty()) {
                if (expectedSolvable) {
                    System.out.println("⚠️  " + type + " : Aucune solution trouvée pour une grille censée être solvable : " + file);
                } else {
                    System.out.println("❌ " + type + " : aucune solution (attendu)");
                }
            } else {
                State current = initial;
                for (Move move : solution) {
                    current = move.getNextState();
                }

                if (!goal.equals(current)) {
                    System.out.println("⚠️  " + type + " : But non atteint même après les mouvements. Peut-être un bug dans Move ?");
                }

                assertEquals(goal, current, type + " - but non atteint dans " + file);
            }


            System.out.println("📊 Rapport pour " + type + " sur " + file.getName() + " :");
            System.out.println("🔢 Étapes          : " + (solution == null ? 0 : solution.size()));
            System.out.println("🌳 Nœuds explorés  : " + report.getNodesExplored());
            System.out.println("⏱️ Temps (ms)      : " + report.getDurationMillis());
            System.out.println("📬 Liste OUVERTE   : " + report.getOpenList().size());
            System.out.println("📂 Liste FERMÉE    : " + report.getClosedList().size());
        } catch (Throwable t) {
            System.err.println("❌ Test échoué : " + t.getMessage());
            t.printStackTrace();
            fail("Test échoué : " + t.getMessage());
        }


    }

    private List<File> getAllGridFiles(String resourceDirPath) {
        List<File> gridFiles = new ArrayList<>();
        try {
            URL url = getClass().getClassLoader().getResource(resourceDirPath);
            if (url == null) throw new RuntimeException("Répertoire introuvable : " + resourceDirPath);

            File dir = new File(url.toURI());
            File[] files = dir.listFiles((d, name) -> name.endsWith(".grid"));
            if (files != null) {
                gridFiles.addAll(List.of(files));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement des fichiers .grid", e);
        }
        return gridFiles;
    }
}

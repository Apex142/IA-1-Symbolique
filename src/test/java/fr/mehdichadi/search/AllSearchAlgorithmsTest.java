package fr.mehdichadi.search;

import fr.mehdichadi.core.GameLoader;
import fr.mehdichadi.core.SearchRunner;
import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.Move;
import fr.mehdichadi.game.State;
import fr.mehdichadi.heuristique.ManhattanDistance;
import fr.mehdichadi.search.report.SearchReport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AllSearchAlgorithmsTest {

    private final List<String> gridFiles = List.of(
            "taquin_2x4b.grid"
    );

    @Test
    @DisplayName("Test avec DFS, BFS et Best-First sur toutes les grilles")
    void testAllFilesWithAllAlgorithms() {
        for (String file : gridFiles) {
            System.out.println("🌐 Test sur : " + file);

            testWithAlgorithm(file, new DepthFirstSearch(), "DFS");

            // TODO : Activer les tests BFS et Best-First et corriger les erreurs
//            testWithAlgorithm(file, new BreadthFirstSearch(), "BFS");
//            testWithAlgorithm(file, new BestFirstSearch(new ManhattanDistance()), "Best-First (Manhattan)");
       }
    }

    void testWithAlgorithm(String file, SearchAlgorithm algorithm, String algoName) {
        GameLoader loader = new GameLoader();
        SearchRunner runner = new SearchRunner();
        Grid initial = loader.loadGame(file);

        assertNotNull(initial, "Initial null pour " + file);
        SearchReport report = runner.runSearch(algorithm, initial);
        List<Move> solution = report.getSolution();

        assertNotNull(solution, algoName + " - solution null pour " + file);
        assertFalse(solution.isEmpty(), algoName + " - solution vide pour " + file);

        State current = initial;
        for (Move move : solution) {
            current = move.getNextState();
        }

        Grid expectedGoal = new Grid(extractGoalGrid(file), null);
        assertEquals(expectedGoal, current, algoName + " - but non atteint dans " + file);

        System.out.println("✅ " + algoName + " : " + solution.size() + " étapes, " +
                report.getNodesExplored() + " nœuds explorés, " +
                report.getDurationMillis() + " ms");
    }

    private String[] extractGoalGrid(String resourcePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            int n = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < n; i++) reader.readLine(); // skip initial
            String[] goal = new String[n];
            for (int i = 0; i < n; i++) goal[i] = reader.readLine();
            return goal;

        } catch (Exception e) {
            throw new RuntimeException("Erreur lecture grille finale : " + resourcePath, e);
        }
    }
}

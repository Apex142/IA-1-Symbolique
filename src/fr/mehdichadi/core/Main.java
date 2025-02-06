package fr.mehdichadi.core;

import fr.mehdichadi.file.factory.GameFileReaderFactory;
import fr.mehdichadi.file.reader.GameFileReader;
import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.Move;
import fr.mehdichadi.game.State;
import fr.mehdichadi.search.BreadthFirstSearch;
import fr.mehdichadi.search.DepthFirstSearch;
import fr.mehdichadi.search.SearchAlgorithm;
import fr.mehdichadi.type.FileType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/resources/taquin_2x4.grid";
        FileType fileType = FileType.GRID;

        // Lecture du fichier et création de l'état initial
        System.out.println("Loading game state from file: " + filePath);
        GameFileReader fileReader = GameFileReaderFactory.getFileReader(fileType);
        Grid initialState = (Grid) fileReader.readGameState(filePath);

        System.out.println("Initial State:");
        System.out.println(initialState);

        System.out.println("Successor States:");
        for (State state : initialState.getSuccessors()) {
            System.out.println(state);
        }

        System.out.println("Goal State:");
        System.out.println(initialState.getGoalGrid());

        // --- Test DFS ---
        System.out.println("\nTesting Depth-First Search...");
        testSearchAlgorithm(new DepthFirstSearch(), initialState);
//
//        // --- Test BFS ---
//        System.out.println("\nTesting Breadth-First Search...");
//        testSearchAlgorithm(new BreadthFirstSearch(), initialState);
    }

    /**
     * Teste un algorithme de recherche et affiche les résultats.
     * @param algorithm Algorithme de recherche (DFS ou BFS).
     * @param initialState État initial du jeu.
     */
    private static void testSearchAlgorithm(SearchAlgorithm algorithm, Grid initialState) {
        System.out.println("Running " + algorithm.getClass().getSimpleName() + "...");

        List<Move> solution = algorithm.search(initialState);

        System.out.println("Solution found:");
        for (Move move : solution) {
            System.out.println(move);
        }
    }
}

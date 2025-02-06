package fr.mehdichadi.search;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;
import fr.mehdichadi.game.Move;

import java.util.*;

public abstract class SearchAlgorithm {

    /**
     * Effectue la recherche d'un chemin entre un état initial et l'état final.
     * @param initialState État de départ.
     * @return Liste des mouvements menant à la solution ou une liste vide si aucune solution trouvée.
     */
    public List<Move> search(State initialState) {
        Set<State> visited = new HashSet<>();
        Map<State, State> cameFrom = new HashMap<>();
        Map<State, Move> moveMap = new HashMap<>();
        Collection<State> openList = getOpenList();

        openList.add(initialState);
        cameFrom.put(initialState, null);

        System.out.println("\n=== Début de la recherche avec " + this.getClass().getSimpleName() + " ===");
        System.out.println("État initial :\n" + initialState);

        while (!openList.isEmpty()) {
            State currentState = removeFromOpenList(openList);

            System.out.println("\nExploration de l'état suivant :");
            System.out.println(currentState);

            if (currentState.isGoalState()) {
                System.out.println("\n✅ Solution trouvée !");
                return reconstructPath(cameFrom, moveMap, currentState);
            }

            if (!visited.contains(currentState)) {
                visited.add(currentState);

                if (currentState instanceof Grid grid) {
                    for (Move move : getPossibleMoves(grid)) {
                        Grid newState = (Grid) move.apply(grid);
                        if (newState != null && !visited.contains(newState)) {
                            openList.add(newState);
                            cameFrom.put(newState, currentState);
                            moveMap.put(newState, move);

                            System.out.println("\nAjout du nouvel état :");
                            System.out.println(newState);
                        }
                    }
                }
            }
        }

        System.out.println("\n❌ Aucun chemin trouvé.");
        return new ArrayList<>(); // Aucun chemin trouvé
    }

    /**
     * Définit la structure de données utilisée pour la liste ouverte.
     * @return Une structure de données appropriée (Stack pour DFS, Queue pour BFS).
     */
    protected abstract Collection<State> getOpenList();

    /**
     * Retire et retourne l'élément à analyser de la liste ouverte.
     * @param openList La liste des états à explorer.
     * @return L'état suivant à traiter.
     */
    protected abstract State removeFromOpenList(Collection<State> openList);

    /**
     * Génère les mouvements possibles à partir d'une grille.
     * @param grid L'état actuel du jeu.
     * @return Liste des mouvements applicables.
     */
    private List<Move> getPossibleMoves(Grid grid) {
        List<Move> moves = new ArrayList<>();
        for (State successor : grid.getSuccessors()) {
            moves.add(new Move(grid, successor));
        }
        return moves;
    }

    /**
     * Reconstruit le chemin de la solution.
     * @param cameFrom Map permettant de retrouver le parent de chaque état.
     * @param moveMap Map associant un état à un mouvement.
     * @param currentState L'état final atteint.
     * @return Liste des mouvements menant à la solution.
     */
    private List<Move> reconstructPath(Map<State, State> cameFrom, Map<State, Move> moveMap, State currentState) {
        List<Move> path = new ArrayList<>();
        System.out.println("\n🔄 Reconstruction du chemin...");
        while (cameFrom.get(currentState) != null) {
            Move move = moveMap.get(currentState);
            path.add(0, move);
            currentState = cameFrom.get(currentState);
        }

        System.out.println("\n📌 Chemin trouvé :");
        for (Move move : path) {
            System.out.println(move);
        }

        return path;
    }
}

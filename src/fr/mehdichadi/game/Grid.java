package fr.mehdichadi.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid implements State {
    private final char[][] grid;
    private final int rows, cols;
    private int emptyRow, emptyCol; // Position de la case vide (' ')
    private final State goalGrid; // L'état final attendu

    /**
     * Constructeur principal : Initialise une grille avec un état final.
     * @param initialGrid Grille initiale sous forme de tableau.
     * @param goalGrid État objectif.
     */
    public Grid(String[] initialGrid, State goalGrid) {
        this.rows = initialGrid.length;
        this.cols = initialGrid[0].length();
        this.grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            this.grid[i] = initialGrid[i].toCharArray();
        }

        this.goalGrid = goalGrid;
        findEmptyTile();
    }

    /**
     * Constructeur utilisé pour copier une grille existante.
     * @param grid Grille sous forme de tableau 2D.
     * @param goalGrid État final attendu.
     */
    private Grid(char[][] grid, State goalGrid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.grid = copyGrid(grid);
        this.goalGrid = goalGrid;
        findEmptyTile();
    }

    /**
     * Trouve la position de la case vide dans la grille.
     */
    private void findEmptyTile() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ' ') {
                    emptyRow = i;
                    emptyCol = j;
                    return;
                }
            }
        }
    }

    /**
     * Vérifie si la grille actuelle correspond à l'état final.
     * @return true si la grille actuelle est la grille objectif, sinon false.
     */
    @Override
    public boolean isGoalState() {
        return goalGrid != null && this.equals(goalGrid);
    }

    /**
     * Génère les états accessibles depuis la grille actuelle.
     * @return Liste des nouveaux états accessibles.
     */
    @Override
    public List<State> getSuccessors() {
        List<State> successors = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Grid newState = move(direction);
            if (newState != null) {
                successors.add(newState);
            }
        }

        return successors;
    }

    /**
     * Déplace la case vide et retourne un nouvel état si possible.
     * @param direction La direction du mouvement.
     * @return Une nouvelle grille après le déplacement, ou null si le déplacement est invalide.
     */
    public Grid move(Direction direction) {
        int newRow = emptyRow + direction.getRowChange();
        int newCol = emptyCol + direction.getColChange();

        if (isValidPosition(newRow, newCol)) {
            char[][] newGrid = copyGrid(this.grid);
            newGrid[emptyRow][emptyCol] = newGrid[newRow][newCol];
            newGrid[newRow][newCol] = ' ';
            return new Grid(newGrid, goalGrid);
        }

        return null;
    }

    /**
     * Vérifie si une position cible est valide.
     * @param row Ligne cible.
     * @param col Colonne cible.
     * @return true si la position est valide, sinon false.
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Copie une grille existante dans un nouveau tableau.
     * @return Une nouvelle copie de la grille.
     */
    private char[][] copyGrid(char[][] sourceGrid) {
        char[][] newGrid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(sourceGrid[i], 0, newGrid[i], 0, cols);
        }
        return newGrid;
    }

    /**
     * Convertit la grille en tableau de chaînes de caractères.
     * @return Un tableau contenant la représentation de la grille.
     */
    public String[] toStringArray() {
        String[] result = new String[grid.length];
        for (int i = 0; i < grid.length; i++) {
            result[i] = new String(grid[i]);
        }
        return result;
    }

    /**
     * Affiche la grille sous forme de texte.
     * @return Une chaîne représentant la grille.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(new String(row)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Affiche la grille objectif sous forme de texte.
     * @return Une chaîne représentant la grille objectif.
     */
    public State getGoalGrid() {
        return goalGrid;
    }

    /**
     * Vérifie si deux grilles sont égales.
     * @param obj L'objet à comparer.
     * @return true si les grilles sont identiques, sinon false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Grid otherGrid = (Grid) obj;
        return Arrays.deepEquals(grid, otherGrid.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }

    /**
     * Retourne la position de la case vide (pour la classe Move).
     * @return Position sous forme de tableau [row, col].
     */
    public int getEmptyRow() {
        return emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }
}

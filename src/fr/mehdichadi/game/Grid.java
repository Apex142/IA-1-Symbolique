package fr.mehdichadi.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid implements State {
    private final char[][] grid;
    private final char[][] goalGrid;
    private final int rows, cols;
    private int emptyRow, emptyCol; // Position de la case vide (' ')

    /**
     * Constructeur de la grille.
     * @param initialGrid Grille initiale.
     * @param goalGrid Grille objectif.
     */
    public Grid(String[] initialGrid, String[] goalGrid) {
        this.rows = initialGrid.length;
        this.cols = initialGrid[0].length();
        this.grid = new char[rows][cols];
        this.goalGrid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            this.grid[i] = initialGrid[i].toCharArray();
            this.goalGrid[i] = goalGrid[i].toCharArray();
        }

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
     * Vérifie si la grille correspond à l'état final.
     * @return true si la grille est dans l'état final, sinon false.
     */
    @Override
    public boolean isGoalState() {
        return Arrays.deepEquals(grid, goalGrid);
    }

    /**
     * Génère les états accessibles depuis la grille actuelle.
     * @return Une liste des grilles accessibles par un déplacement.
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
     * Déplace la case vide dans une direction et retourne une nouvelle grille.
     * @param direction La direction du mouvement.
     * @return Une nouvelle grille avec le mouvement appliqué, ou null si impossible.
     */
    public Grid move(Direction direction) {
        int newRow = emptyRow, newCol = emptyCol;

        switch (direction) {
            case UP -> newRow--;
            case DOWN -> newRow++;
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
        }

        if (isValidPosition(newRow, newCol)) {
            char[][] newGrid = copyGrid();
            newGrid[emptyRow][emptyCol] = newGrid[newRow][newCol];
            newGrid[newRow][newCol] = ' ';
            return new Grid(toStringArray(newGrid), toStringArray(goalGrid));
        }

        return null;
    }

    /**
     * Vérifie si une position est valide dans la grille.
     * @param row Ligne cible.
     * @param col Colonne cible.
     * @return true si la position est valide, sinon false.
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Copie la grille actuelle dans un nouveau tableau.
     * @return Une copie de la grille.
     */
    private char[][] copyGrid() {
        char[][] newGrid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(grid[i], 0, newGrid[i], 0, cols);
        }
        return newGrid;
    }

    /**
     * Convertit une grille en tableau de chaînes de caractères.
     * @param grid La grille à convertir.
     * @return Un tableau de chaînes représentant la grille.
     */
    private String[] toStringArray(char[][] grid) {
        String[] result = new String[grid.length];
        for (int i = 0; i < grid.length; i++) {
            result[i] = new String(grid[i]);
        }
        return result;
    }

    /**
     * Affiche la grille sous forme textuelle.
     * @return Une représentation sous forme de chaîne de la grille.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(new String(row)).append("\n");
        }
        return sb.toString();
    }
}

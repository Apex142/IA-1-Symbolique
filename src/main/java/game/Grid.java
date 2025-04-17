package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid implements State {
    private final char[][] grid;
    private final int rows, cols;
    private int emptyRow, emptyCol;

    public Grid(String[] initialGrid) {
        this.rows = initialGrid.length;
        this.cols = initialGrid[0].length();
        this.grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            this.grid[i] = initialGrid[i].toCharArray();
        }

        findEmptyTile();
    }

    private Grid(char[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.grid = copyGrid(grid);
        findEmptyTile();
    }

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

    @Override
    public List<State> getSuccessors() {
        List<State> successors = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Grid newState = move(direction);
            if (newState != null) successors.add(newState);
        }
        return successors;
    }

    public Grid move(Direction direction) {
        int newRow = emptyRow + direction.getRowChange();
        int newCol = emptyCol + direction.getColChange();

        if (isValidPosition(newRow, newCol)) {
            char[][] newGrid = copyGrid(this.grid);
            newGrid[emptyRow][emptyCol] = newGrid[newRow][newCol];
            newGrid[newRow][newCol] = ' ';
            return new Grid(newGrid);
        }
        return null;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private char[][] copyGrid(char[][] sourceGrid) {
        char[][] newGrid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(sourceGrid[i], 0, newGrid[i], 0, cols);
        }
        return newGrid;
    }

    public String[] toStringArray() {
        String[] result = new String[grid.length];
        for (int i = 0; i < grid.length; i++) {
            result[i] = new String(grid[i]);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(new String(row)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Grid other = (Grid) obj;

        if (this.rows != other.getRows() || this.cols != other.getCols()) return false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.getTile(i, j) != other.getTile(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public int hashCode() {
        int result = 17;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result = 31 * result + grid[i][j];
            }
        }
        return result;
    }


    public int getEmptyRow() {
        return emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char getTile(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Position hors limites de la grille.");
        }
        return grid[row][col];
    }

    public char getEmptyTile() {
        return grid[emptyRow][emptyCol];
    }
}

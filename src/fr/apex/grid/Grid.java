package fr.apex.grid;

import java.util.Arrays;
import java.util.Objects;

public class Grid {
    private final int rows;
    private final int cols;
    private final char[][] grid;

    public Grid(int rows, int cols, char[][] grid) {
        this.rows = rows;
        this.cols = cols;
        this.grid = grid;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Grid other)) return false;
        return Arrays.deepEquals(this.grid, other.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(grid));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(new String(row)).append("\n");
        }
        return sb.toString();
    }
}

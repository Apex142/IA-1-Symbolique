package heuristique;

import game.Grid;
import game.State;

public class ManhattanPlusLinearConflict implements Heuristique {

    @Override
    public int evaluate(State state, Grid goalGrid) {
        if (!(state instanceof Grid current)) {
            throw new IllegalArgumentException("State must be an instance of Grid");
        }

        int rows = current.getRows();
        int cols = current.getCols();
        int totalDistance = 0;
        int linearConflicts = 0;

        // Compute Manhattan distance and linear conflict
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char tile = current.getTile(row, col);
                if (tile != ' ') {
                    int[] goalPos = findTilePosition(goalGrid, tile);
                    int goalRow = goalPos[0];
                    int goalCol = goalPos[1];
                    totalDistance += Math.abs(row - goalRow) + Math.abs(col - goalCol);

                    // Linear conflict row
                    if (row == goalRow) {
                        for (int k = col + 1; k < cols; k++) {
                            char otherTile = current.getTile(row, k);
                            if (otherTile != ' ') {
                                int[] otherGoalPos = findTilePosition(goalGrid, otherTile);
                                if (otherGoalPos[0] == row && otherGoalPos[1] < goalCol) {
                                    linearConflicts++;
                                }
                            }
                        }
                    }

                    // Linear conflict column
                    if (col == goalCol) {
                        for (int k = row + 1; k < rows; k++) {
                            char otherTile = current.getTile(k, col);
                            if (otherTile != ' ') {
                                int[] otherGoalPos = findTilePosition(goalGrid, otherTile);
                                if (otherGoalPos[1] == col && otherGoalPos[0] < goalRow) {
                                    linearConflicts++;
                                }
                            }
                        }
                    }
                }
            }
        }

        return totalDistance + 2 * linearConflicts;
    }

    private int[] findTilePosition(Grid grid, char tile) {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                if (grid.getTile(row, col) == tile) {
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalArgumentException("Tile not found in goal grid: " + tile);
    }
}

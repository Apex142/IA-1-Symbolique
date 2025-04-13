package fr.mehdichadi.heuristique;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;

/**
 * Heuristic that calculates the total Manhattan Distance between each tile's current
 * position and its target position in the goal grid.
 */
public class ManhattanDistance implements Heuristique {

    /**
     * Calculates the Manhattan distance heuristic for a given state.
     *
     * @param state     The current state (must be an instance of Grid).
     * @param goalGrid  The goal state represented as a 2D character array.
     * @return          The total Manhattan distance between current and goal tile positions.
     */
    @Override
    public int evaluate(State state, char[][] goalGrid) {
        if (!(state instanceof Grid)) {
            throw new IllegalArgumentException("State must be an instance of Grid.");
        }

        Grid currentGrid = (Grid) state;
        int distance = 0;

        for (int row = 0; row < currentGrid.getRows(); row++) {
            for (int col = 0; col < currentGrid.getCols(); col++) {
                char tile = currentGrid.getTile(row, col);
                if (tile != ' ') { // Don't count the empty tile
                    // Find goal position of the tile in the goal grid
                    int[] goalPosition = findTilePosition(goalGrid, tile);
                    if (goalPosition != null) {
                        distance += Math.abs(row - goalPosition[0]) + Math.abs(col - goalPosition[1]);
                    }
                }
            }
        }

        return distance;
    }

    /**
     * Helper method to find the row and column of a tile in a 2D goal grid.
     *
     * @param grid The goal grid.
     * @param tile The tile to find.
     * @return     An array [row, col] with the tile's position, or null if not found.
     */
    private int[] findTilePosition(char[][] grid, char tile) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == tile) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // Tile not found in the grid
    }
}

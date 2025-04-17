package heuristique;

import game.Grid;
import game.State;

/**
 * Heuristic that calculates the total Manhattan Distance between each tile's current
 * position and its target position in the goal grid.
 */
public class ManhattanDistance implements Heuristique {

    /**
     * Calculates the Manhattan distance heuristic for a given state.
     *
     * @param state     The current state (must be an instance of Grid).
     * @param goalGrid  The goal state as a Grid.
     * @return          The total Manhattan distance between current and goal tile positions.
     */
    @Override
    public int evaluate(State state, Grid goalGrid) {
        if (!(state instanceof Grid)) {
            throw new IllegalArgumentException("State must be an instance of Grid.");
        }

        Grid currentGrid = (Grid) state;
        int distance = 0;

        for (int row = 0; row < currentGrid.getRows(); row++) {
            for (int col = 0; col < currentGrid.getCols(); col++) {
                char tile = currentGrid.getTile(row, col);
                if (tile != ' ') {
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
     * Finds the position of a tile in the goal Grid.
     *
     * @param grid The goal grid.
     * @param tile The tile to find.
     * @return     An array [row, col] with the tile's position, or null if not found.
     */
    private int[] findTilePosition(Grid grid, char tile) {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                if (grid.getTile(row, col) == tile) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }
}

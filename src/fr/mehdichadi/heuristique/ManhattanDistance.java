package fr.mehdichadi.heuristique;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;

public class ManhattanDistance implements Heuristique {

    @Override
    public int evaluate(State state, char[][] goalGrid) {
        if (!(state instanceof Grid)) {
            throw new IllegalArgumentException("Both state and goalState must be instances of Grid.");
        }

        Grid currentGrid = (Grid) state;

        int distance = 0;
        for (int row = 0; row < currentGrid.getRows(); row++) {
            for (int col = 0; col < currentGrid.getCols(); col++) {
                char tile = currentGrid.getTile(row, col);
                if (tile != ' ') {
                    int[] goalPosition = new Grid(currentGrid.toStringArray(goalGrid), currentGrid.toStringArray(goalGrid)).findTile(tile);
                    distance += Math.abs(row - goalPosition[0]) + Math.abs(col - goalPosition[1]);
                }
            }
        }
        return distance;
    }
}
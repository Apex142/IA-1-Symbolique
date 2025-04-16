package fr.mehdichadi.heuristique;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;

public class ManhattanDistance implements Heuristique {

    // fait une fonction qui calcule la distance de Manhattan entre deux cases et utilise evaluate de Heuristique public int evaluate(State state, char[][] goalGrid) {
    @Override
    public int evaluate(State state, char[][] goalGrid) {
        Grid grid = (Grid) state;
        int distance = 0;

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                if (grid.getTile(i, j) != ' ') {
                    distance += manhattanDistance(i, j, grid.getTile(i, j), goalGrid);
                }
            }
        }

        return distance;
    }

    // fait manhanttanDistance
    private int manhattanDistance(int row, int col, char tile, char[][] goalGrid) {
        for (int i = 0; i < goalGrid.length; i++) {
            for (int j = 0; j < goalGrid[0].length; j++) {
                if (goalGrid[i][j] == tile) {
                    return Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }

        return -1;
    }
}
package heuristique;

import game.Grid;
import game.State;

public class MisplacedTilesHeuristic implements Heuristique {

    @Override
    public int evaluate(State state, Grid goalGrid) {
        if (!(state instanceof Grid)) {
            throw new IllegalArgumentException("State must be an instance of Grid.");
        }

        Grid current = (Grid) state;
        int count = 0;

        for (int row = 0; row < current.getRows(); row++) {
            for (int col = 0; col < current.getCols(); col++) {
                char tile = current.getTile(row, col);
                char goalTile = goalGrid.getTile(row, col);

                // On ne compte pas la case vide
                if (tile != ' ' && tile != goalTile) {
                    count++;
                }
            }
        }

        return count;
    }
}

package file.reader;

import game.Grid;
import game.State;

import java.io.InputStream;

public interface GameFileReader {
    State readGameState(InputStream inputStream);

    Grid getGoalGrid();

    Grid getInitialGrid();
}

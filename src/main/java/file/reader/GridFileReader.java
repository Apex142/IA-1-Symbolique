package file.reader;

import game.Grid;
import game.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GridFileReader implements GameFileReader {

    private Grid initialGrid;
    private Grid goalGrid;

    @Override
    public State readGameState(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int gridSize = Integer.parseInt(reader.readLine().trim());
            String[] initialGridStr = new String[gridSize];
            String[] goalGridStr = new String[gridSize];

            for (int i = 0; i < gridSize; i++) {
                initialGridStr[i] = reader.readLine();
            }

            for (int i = 0; i < gridSize; i++) {
                goalGridStr[i] = reader.readLine();
            }

            this.initialGrid = new Grid(initialGridStr);
            this.goalGrid = new Grid(goalGridStr);

            return initialGrid;
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error reading .grid file from input stream", e);
        }
    }

    @Override
    public Grid getInitialGrid() {
        return initialGrid;
    }

    @Override
    public Grid getGoalGrid() {
        return goalGrid;
    }
}

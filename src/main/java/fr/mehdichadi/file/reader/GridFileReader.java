package fr.mehdichadi.file.reader;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GridFileReader implements GameFileReader {

    @Override
    public State readGameState(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int gridSize = Integer.parseInt(reader.readLine().trim());
            String[] initialGrid = new String[gridSize];
            String[] goalGrid = new String[gridSize];

            for (int i = 0; i < gridSize; i++) {
                initialGrid[i] = reader.readLine();
            }

            for (int i = 0; i < gridSize; i++) {
                goalGrid[i] = reader.readLine();
            }

            State goalState = new Grid(goalGrid, null);
            return new Grid(initialGrid, goalState);

        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error reading .grid file from input stream", e);
        }
    }
}

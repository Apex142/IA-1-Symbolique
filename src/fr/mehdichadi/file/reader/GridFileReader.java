package fr.mehdichadi.file.reader;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GridFileReader implements GameFileReader {
    @Override
    public State readGameState(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int gridSize = Integer.parseInt(reader.readLine().trim());
            String[] initialGrid = new String[gridSize];
            String[] goalGrid = new String[gridSize];

            for (int i = 0; i < gridSize; i++) {
                initialGrid[i] = reader.readLine();
            }
            for (int i = 0; i < gridSize; i++) {
                goalGrid[i] = reader.readLine();
            }

            return new Grid(initialGrid, goalGrid);
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error reading .grid file: " + filePath, e);
        }
    }
}
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
            int gridSize = Integer.parseInt(reader.readLine().trim()); // Taille de la grille
            String[] initialGrid = new String[gridSize];
            String[] goalGrid = new String[gridSize];

            // Lecture de la grille initiale
            for (int i = 0; i < gridSize; i++) {
                initialGrid[i] = reader.readLine();
            }

            // Lecture de la grille objectif
            for (int i = 0; i < gridSize; i++) {
                goalGrid[i] = reader.readLine();
            }

            // Création de l'état final en tant que `State`
            State goalState = new Grid(goalGrid, null);

            // Retourne la grille initiale avec la référence à l'état final
            return new Grid(initialGrid, goalState);
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error reading .grid file: " + filePath, e);
        }
    }
}

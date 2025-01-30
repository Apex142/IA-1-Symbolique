package fr.apex.grid.strategy;

import fr.apex.grid.Grid;
import fr.apex.grid.GridGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GridFileReader implements GridReaderStrategy {

    @Override
    public GridGame readFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int rows = Integer.parseInt(br.readLine().trim());
            char[][] initialGrid = new char[rows][];
            char[][] finalGrid = new char[rows][];

            // Lecture de la grille initiale
            for (int i = 0; i < rows; i++) {
                initialGrid[i] = br.readLine().toCharArray();
            }

            // Lecture de la grille finale
            for (int i = 0; i < rows; i++) {
                finalGrid[i] = br.readLine().toCharArray();
            }

            return new GridGame(new Grid(rows, initialGrid[0].length, initialGrid),
                    new Grid(rows, finalGrid[0].length, finalGrid));
        }
    }
}

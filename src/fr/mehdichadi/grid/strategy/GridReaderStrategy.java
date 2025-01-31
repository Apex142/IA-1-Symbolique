package fr.mehdichadi.grid.strategy;

import fr.mehdichadi.grid.GridGame;

import java.io.IOException;

public interface GridReaderStrategy {
    GridGame readFromFile(String filePath) throws IOException;
}

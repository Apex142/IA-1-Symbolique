package fr.apex.grid.strategy;

import fr.apex.grid.GridGame;

import java.io.IOException;

public interface GridReaderStrategy {
    GridGame readFromFile(String filePath) throws IOException;
}

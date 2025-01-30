package fr.apex.grid.reader;

import fr.apex.grid.GridGame;
import fr.apex.grid.strategy.GridReaderStrategy;

import java.io.IOException;

public class GridReader {
    private final GridReaderStrategy strategy;

    public GridReader(GridReaderStrategy strategy) {
        this.strategy = strategy;
    }

    public GridGame read(String filePath) throws IOException {
        return strategy.readFromFile(filePath);
    }
}

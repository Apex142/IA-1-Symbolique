package fr.mehdichadi.grid.reader;

import fr.mehdichadi.grid.GridGame;
import fr.mehdichadi.grid.strategy.GridReaderStrategy;

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

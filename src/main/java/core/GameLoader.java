package core;

import file.factory.GameFileReaderFactory;
import file.reader.GameFileReader;
import file.reader.GridFileReader;
import game.Grid;
import type.FileType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GameLoader {

    private Grid initialGrid;
    private Grid goalGrid;

    public Grid loadGame(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            if (inputStream == null) {
                throw new RuntimeException("Fichier introuvable dans resources : " + file.getName());
            }

            GridFileReader reader = (GridFileReader) GameFileReaderFactory.getFileReader(FileType.GRID);
            Grid initialGridObject = (Grid) reader.readGameState(inputStream);
            this.initialGrid = reader.getInitialGrid();
            this.goalGrid = reader.getGoalGrid();
            return initialGridObject;

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier : " + file.getName(), e);
        }
    }

    public Grid getInitialGrid() {
        return initialGrid;
    }

    public Grid getGoalGrid() {
        return goalGrid;
    }
}

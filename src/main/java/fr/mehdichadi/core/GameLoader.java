package fr.mehdichadi.core;

import fr.mehdichadi.file.factory.GameFileReaderFactory;
import fr.mehdichadi.file.reader.GameFileReader;
import fr.mehdichadi.game.Grid;
import fr.mehdichadi.type.FileType;

import java.io.IOException;
import java.io.InputStream;

public class GameLoader {

    public Grid loadGame(String resourceFileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceFileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Fichier introuvable dans resources : " + resourceFileName);
            }

            GameFileReader reader = GameFileReaderFactory.getFileReader(FileType.GRID);
            return (Grid) reader.readGameState(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier : " + resourceFileName, e);
        }
    }
}


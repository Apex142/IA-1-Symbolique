package fr.mehdichadi.file.reader;

import fr.mehdichadi.game.State;

public interface GameFileReader {
    State readGameState(String filePath);
}

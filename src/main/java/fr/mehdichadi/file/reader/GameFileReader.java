package fr.mehdichadi.file.reader;

import fr.mehdichadi.game.State;

import java.io.InputStream;

public interface GameFileReader {
    State readGameState(InputStream inputStream);
}

package fr.mehdichadi.file.reader;

import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GridFileReaderTest {

    @Test
    void testReadGameState() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("taquin_2x4.grid");
        assertNotNull(is);

        GridFileReader reader = new GridFileReader();
        State result = reader.readGameState(is);

        assertNotNull(result);
        assertTrue(result instanceof Grid);
        assertNotNull(((Grid) result).getGoalGrid());
    }
}

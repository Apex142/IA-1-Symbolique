import file.reader.GridFileReader;
import game.Grid;
import game.State;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GridFileReaderTest {

    @Test
    void testReadGameState() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("grids/taquin_2x4.grid");
        assertNotNull(is, "Le fichier de test n'a pas été trouvé");

        GridFileReader reader = new GridFileReader();
        State result = reader.readGameState(is);

        assertNotNull(result, "Résultat null");
        assertTrue(result instanceof Grid, "Le résultat n'est pas une Grid");

        Grid initial = (Grid) result;
        Grid goal = reader.getGoalGrid();

        assertNotNull(goal, "La grille but (goalGrid) est null");
        assertEquals(initial.getRows(), goal.getRows(), "Nombre de lignes différent entre initial et goal");
        assertEquals(initial.getCols(), goal.getCols(), "Nombre de colonnes différent entre initial et goal");
    }
}

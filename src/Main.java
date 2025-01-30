import fr.apex.grid.GridGame;
import fr.apex.grid.reader.GridReader;
import fr.apex.grid.strategy.GridFileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "taquin_5x5b.grid"; // Chemin du fichier

            // Utilisation de la Factory Method avec une stratégie de lecture fichier
            GridReader gridReader = new GridReader(new GridFileReader());
            GridGame game = gridReader.read(filePath);

            System.out.println("Jeu du Taquin chargé avec succès !");
            System.out.println(game);

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
}

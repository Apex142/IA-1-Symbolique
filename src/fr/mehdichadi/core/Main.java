package fr.mehdichadi.core;

import fr.mehdichadi.file.factory.GameFileReaderFactory;
import fr.mehdichadi.file.reader.GameFileReader;
import fr.mehdichadi.game.Direction;
import fr.mehdichadi.game.Grid;
import fr.mehdichadi.game.State;
import fr.mehdichadi.type.FileType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Chemin du fichier de jeu .grid (À modifier selon votre cas)
        String filePath = "src/resources/taquin_3x3.grid";
        FileType fileType = FileType.GRID;

        // Lecture du fichier et création de l'état initial
        System.out.println("Loading game state from file: " + filePath);
        GameFileReader fileReader = GameFileReaderFactory.getFileReader(fileType);
        Grid initialState = (Grid) fileReader.readGameState(filePath);

        System.out.println("Initial State:");
        System.out.println(initialState);

        System.out.println("succesor states:");
        for (State state : initialState.getSuccessors()) {
            System.out.println(state);
        }

        System.out.println("Goal State :");
        System.out.println(initialState.toStringGoalGrid());




    }
}

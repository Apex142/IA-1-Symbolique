package fr.mehdichadi.file.factory;

import fr.mehdichadi.file.reader.GameFileReader;
import fr.mehdichadi.file.reader.GridFileReader;
import fr.mehdichadi.type.FileType;

public class GameFileReaderFactory {
    public static GameFileReader getFileReader(FileType fileType) {
        return switch (fileType) {
            case GRID -> new GridFileReader();
            default -> throw new IllegalArgumentException("Unsupported file type: " + fileType);
        };
    }
}

package file.factory;

import file.reader.GameFileReader;
import file.reader.GridFileReader;
import type.FileType;

public class GameFileReaderFactory {
    public static GameFileReader getFileReader(FileType fileType) {
        return switch (fileType) {
            case GRID -> new GridFileReader();
            default -> throw new IllegalArgumentException("Unsupported file type: " + fileType);
        };
    }
}

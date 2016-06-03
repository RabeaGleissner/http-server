package de.rabea.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileParser {

    private String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }

    public String read() {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

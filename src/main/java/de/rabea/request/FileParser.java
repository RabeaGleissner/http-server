package de.rabea.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.copyOfRange;

public class FileParser {

    private String filePath;
    private int start;
    private int end;
    private boolean partial;

    public FileParser(String filePath) {
        this.filePath = filePath;
        this.partial = false;
    }

    public FileParser(String filePath, int[] range) {
        this(filePath);
        this.start = range[0];
        this.end = range[1];
        this.partial = true;
    }

    public byte[] read() {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            if (partial) {
                fileContent = copyOfRange(fileContent, start, end);
            }
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

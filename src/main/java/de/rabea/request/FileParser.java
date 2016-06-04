package de.rabea.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileParser {

    private String filePath;
    private int start;
    private int end;
    private boolean partial;

    public FileParser(String filePath) {
        this.filePath = filePath;
        this.partial = false;
    }

    public FileParser(String filePath, int start, int end) {
        this(filePath);
        this.start = start;
        this.end = end;
        this.partial = true;
    }

    public String read() {
        String content;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            if (partial) {
                byte[] partial = Arrays.copyOfRange(bytes, start, end);
                content = new String(partial);
            } else {
                content = new String(bytes);
            }
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package de.rabea.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.copyOfRange;

public class FileParser {

    private final String filePath;
    private boolean reverse;
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

    public FileParser(String filePath, ReadInstructions instructions) {
        this(filePath);
        this.start = instructions.start();
        this.end = instructions.end();
        this.partial = instructions.partial();
        this.reverse = instructions.reverse();
    }

    public byte[] read() {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            if (partial && (start != -1 && end != -1)) {
                fileContent = copyOfRange(fileContent, start, end);
            } else if (partial && reverse) {
                fileContent = copyOfRange(fileContent, fileContent.length - start, fileContent.length);
            } else if (partial && end == -1) {
                fileContent = copyOfRange(fileContent, start, fileContent.length);
            }
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

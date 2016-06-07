package de.rabea.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.copyOfRange;

public class FileParser {

    private final String filePath;
    private String range;
    private boolean partial;

    public FileParser(String filePath) {
        this.filePath = filePath;
        this.partial = false;
    }

    public FileParser(String filePath, String range) {
        this(filePath);
        this.range = range;
        this.partial = true;
    }

    public byte[] read() {
        try {
            byte[] fileContent = readCompleteFile();
            if (partial) {
                fileContent = readPartialFile(fileContent);
            }
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] readCompleteFile() throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    private byte[] readPartialFile(byte[] fileContent) {
        String firstChar = range.substring(0,1);
        String secondChar = range.substring(1,2);
        String thirdChar = range.substring(2);
        int from = 0;
        int to = 0;

        if (startAndEndGiven(range)) {
            from = toInteger(firstChar);
            to = toInteger(thirdChar) + 1;
        } else if (reverseStart(range)) {
            from = fileContent.length - toInteger(secondChar);
            to = fileContent.length;
        } else if (onlyStartGiven(secondChar)) {
            from = toInteger(firstChar);
            to = fileContent.length;
        }
        return readPartialContent(from, to, fileContent);
    }

    private byte[] readPartialContent(int from, int to, byte[] fileContent) {
        return copyOfRange(fileContent, from, to);
    }

    private boolean onlyStartGiven(String secondChar) {
        return secondChar.equals("-") && range.length() == 2;
    }

    private boolean reverseStart(String startEnd) {
        return startEnd.substring(0,1).equals("-");
    }

    private boolean startAndEndGiven(String startEnd) {
        return startEnd.length() == 3;
    }

    private int toInteger(String number) {
        return Integer.parseInt(number);
    }
}

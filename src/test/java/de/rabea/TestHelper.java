package de.rabea;

import de.rabea.request.FileParser;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestHelper {

    public static String asString(byte[] bytes) {
        return new String(bytes, UTF_8);
    }

    public static String directory() {
        return System.getProperty("user.dir") + "/src/test/java/de/rabea/request/resource";
    }

    public static void resetFileContent() {
        FileParser fileParser = new FileParser(directory() + "/file.txt");
        fileParser.updateExistingFile("Some content");
    }
}

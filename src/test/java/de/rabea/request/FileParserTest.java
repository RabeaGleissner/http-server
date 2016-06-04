package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileParserTest {
    private String file = new Directory().forResource() + "/file.txt";

    @Test
    public void readFileContents() {
        FileParser fileParser = new FileParser(file);
        assertEquals("Some content", fileParser.read());
    }

    @Test
    public void readsPartialContentIfRangeIsGiven() {
        FileParser fileParser = new FileParser(file, 0, 4);
        assertEquals("Some", fileParser.read());
    }
}
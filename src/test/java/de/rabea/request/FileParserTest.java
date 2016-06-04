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
        int[] range = {0,4};
        FileParser fileParser = new FileParser(file, range);
        assertEquals("Some", fileParser.read());
    }
}
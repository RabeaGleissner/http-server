package de.rabea.request;

import org.junit.Test;

import static de.rabea.Helper.asString;
import static org.junit.Assert.assertEquals;

public class FileParserTest {
    private String file = new Directory().forResource() + "/file.txt";

    @Test
    public void readFileContents() {
        FileParser fileParser = new FileParser(file);
        assertEquals("Some content", asString(fileParser.read()));
    }

    @Test
    public void readsPartialContentIfRangeIsGiven() {
        int[] range = {0,4};
        FileParser fileParser = new FileParser(file, range);
        assertEquals("Some", asString(fileParser.read()));
    }

}
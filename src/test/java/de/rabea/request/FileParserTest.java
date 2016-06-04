package de.rabea.request;

import org.junit.Test;

import static de.rabea.Helper.asString;
import static org.junit.Assert.assertEquals;

public class FileParserTest {
    private final String file = new Directory().forResource() + "/file.txt";

    @Test
    public void readFileContents() {
        FileParser fileParser = new FileParser(file);
        assertEquals("Some content", asString(fileParser.read()));
    }

    @Test
    public void readsPartialContentIfRangeIsGiven() {
        FileParser fileParser = new FileParser(file, "0-4");
        assertEquals("Some ", asString(fileParser.read()));
    }

    @Test
    public void readsPartialContentWithOnlyRangeEndGiven() {
        FileParser fileParser = new FileParser(file, "-6");
        assertEquals("ontent", asString(fileParser.read()));
    }

    @Test
    public void readsPartialContentWithOnlyRangeStartGiven() {
        FileParser fileParser = new FileParser(file, "4-");
        assertEquals(" content", asString(fileParser.read()));
    }
}
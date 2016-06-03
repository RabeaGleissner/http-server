package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileParserTest {

    @Test
    public void readFileContents() {
        FileParser fileParser = new FileParser(new Directory().forResource() + "/file.txt");
        assertEquals("Some content", fileParser.read());
    }
}
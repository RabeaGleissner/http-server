package de.rabea.request;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static de.rabea.TestHelper.*;
import static org.junit.Assert.assertEquals;

public class FileParserTest {
    private String pathToFile;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        pathToFile = createFileWithContent().getAbsolutePath();
        resetFileContent();
    }

    @Test
    public void readFileContents() throws IOException {
        FileParser fileParser = new FileParser(pathToFile);
        assertEquals("Some content", asString(fileParser.read()));
    }

    @Test
    public void readsPartialContentIfRangeIsGiven() {
        FileParser fileParser = new FileParser(pathToFile, "0-4");
        assertEquals("Some ", asString(fileParser.read()));
    }

    @Test
    public void readsPartialContentWithOnlyRangeEndGiven() {
        FileParser fileParser = new FileParser(pathToFile, "-6");
        assertEquals("ontent", asString(fileParser.read()));
    }

    @Test
    public void readsPartialContentWithOnlyRangeStartGiven() {
        FileParser fileParser = new FileParser(pathToFile, "4-");
        assertEquals(" content", asString(fileParser.read()));
    }

    @Test
    public void updatesFileWithGivenContent() {
        FileParser fileParser = new FileParser(pathToFile);
        fileParser.updateExistingFile("updated");
        assertEquals("updated", asString(fileParser.read()));
    }

    private File createFileWithContent() throws IOException {
        File testFile = testFolder.newFile("file.txt");
        FileWriter writer = new FileWriter(testFile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write("Some content");
        bufferedWriter.close();
        return testFile;
    }

}
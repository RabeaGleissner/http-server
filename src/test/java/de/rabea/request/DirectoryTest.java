package de.rabea.request;

import de.rabea.TestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectoryTest {

    private String directoryPath;

    @Before
    public void setup() {
        this.directoryPath = TestHelper.directory();
    }

    @Test
    public void returnsTrueIfResourceIsInPublicDirectory() {
        Directory directory = new Directory(directoryPath);
        assertTrue(directory.isInDirectory("/file.txt"));
    }
}
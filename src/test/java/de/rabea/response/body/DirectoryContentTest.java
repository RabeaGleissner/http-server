package de.rabea.response.body;

import de.rabea.request.Directory;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class DirectoryContentTest {
    @Test
    public void returnsLinksToDirectoryContents() {
        DirectoryContent directoryContent = new DirectoryContent(new Directory(directory()));
        assertEquals("<a href=/file.txt>file.txt</a>", asString(directoryContent.response()));
    }
}
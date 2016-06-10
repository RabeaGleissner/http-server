package de.rabea.response.body;

import de.rabea.request.Directory;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class FileContentTest {

    @Test
    public void returnsFileContent() {
        FileContent fileContent = new FileContent("/file.txt", "2-6", new Directory(directory()), true);
        assertEquals("me co", asString(fileContent.response()));
    }

    @Test
    public void returnsEmptyByteArrayIfNoFileContent() {
        FileContent fileContent = new FileContent("/no-file.txt", "2-6", new Directory(directory()), true);
        assertEquals("", asString(fileContent.response()));
    }
}
package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.Request;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ResponseBodyTest {

    private Request requestToFile;
    private Directory directory;

    @Before
    public void setup() {
        directory = new Directory(directory());
        requestToFile = new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4", directory);
    }

    @Test
    public void returnsFileContentIfRequestToFileIsMade() {
        ResponseBody body = new ResponseBody(requestToFile);
        assertEquals("Some ", asString(body.create()));
    }
}
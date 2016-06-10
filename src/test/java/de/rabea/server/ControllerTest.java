package de.rabea.server;

import de.rabea.request.Directory;
import de.rabea.request.FileParser;
import de.rabea.request.Log;
import de.rabea.request.Request;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.*;
import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private Request requestWithBody;
    private Request requestWithParams;
    private Request getRequest;
    private Request getRequestWithEmptyDirectory;
    private Directory directory;

    @Before
    public void setup() {
        directory = new Directory(directory());
        requestWithBody= new Request("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "\n" +
                "data=fatcat", directory);
        requestWithParams = new Request("GET /parameters?code=hello HTTP/1.1", directory);
        getRequestWithEmptyDirectory = new Request("GET / HTTP/1.1", new Directory("PUBLIC_DIR"));
        getRequest = new Request("GET / HTTP/1.1", directory);
    }

    @Test
    public void returnsLogsIfRequired() {
        Controller controller = new Controller(new Request("GET /logs HTTP/1.1", directory), new Log());
        assertEquals("GET /logs HTTP/1.1\n", asString(controller.action().response()));
    }

    @Test
    public void returnsPostRequestBodyAsBytes() {
        Controller controller = new Controller(requestWithBody, new Log());
        assertEquals("data=fatcat", asString(controller.action().response()));
    }

    @Test
    public void returnsParams() {
        Controller controller = new Controller(requestWithParams, new Log());
        assertEquals("code = hello\n", asString(controller.action().response()));
    }

    @Test
    public void returnsEmptyStringIfItHasNotReceivedMessages() {
        Controller controller = new Controller(getRequestWithEmptyDirectory, new Log());
        assertEquals("", asString(controller.action().response()));
    }

    @Test
    public void returnsLinksToFilesInDirectory() {
        Controller controller = new Controller(getRequest, new Log());
        assertEquals("<a href=/file.txt>file.txt</a>", asString(controller.action().response()));
    }

    @Test
    public void updatesFileForPatchRequestWithEtag() {
        FileParser fileParser = new FileParser(directory() + "/file.txt");
        Request request = new Request("PATCH /file.txt HTTP/1.1\n" +
                "If-Match: dc50a0d27dda2eee9f6\n" +
                "Content-Length: 15\n" +
                "\n" +
                "patched content\n", directory);
        Controller controller = new Controller(request, new Log());
        controller.updateFile();

        assertEquals("patched content", asString(fileParser.read()));

        resetFileContent();
    }
}
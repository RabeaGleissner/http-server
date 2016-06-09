package de.rabea.server;

import de.rabea.request.Directory;
import de.rabea.request.Log;
import de.rabea.request.Request;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private Request requestWithBody;
    private Request requestWithParams;
    private Request requestToFile;
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
        requestToFile = new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4", directory);
        getRequestWithEmptyDirectory = new Request("GET / HTTP/1.1", new Directory("PUBLIC_DIR"));
        getRequest = new Request("GET / HTTP/1.1", directory);
    }

    @Test
    public void returnsLogsIfRequired() {
        Controller controller = new Controller(new Request("GET /logs HTTP/1.1", directory), new Log());
        assertEquals("GET /logs HTTP/1.1\n", controller.action().response());
    }

    @Test
    public void returnsPostRequestBodyAsBytes() {
        Controller controller = new Controller(requestWithBody, new Log());
        assertEquals("data=fatcat", controller.action().response());
    }

    @Test
    public void returnsParams() {
        Controller controller = new Controller(requestWithParams, new Log());
        assertEquals("code = hello\n", controller.action().response());
    }

    @Test
    public void returnsEmptyStringIfItHasNotReceivedMessages() {
        Controller controller = new Controller(getRequestWithEmptyDirectory, new Log());
        assertEquals("", controller.action().response());
    }

    @Test
    public void returnsLinksToFilesInDirectory() {
        Controller controller = new Controller(getRequest, new Log());
        assertEquals("<a href=/file.txt>file.txt</a>", controller.action().response());
    }
}
package de.rabea.server;

import de.rabea.request.Directory;
import de.rabea.request.Log;
import de.rabea.request.Request;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ControllerTest {

    @Test
    public void returnsLogsIfRequired() {
        Controller controller = new Controller(new Request("GET /logs HTTP/1.1", new Directory(directory())), new Log());
        assertEquals("GET /logs HTTP/1.1\n", controller.action().response());
    }

    @Test
    public void returnsPostRequestBodyAsBytes() {
        Controller controller = new Controller(new Request("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat", new Directory(directory())), new Log());
        assertEquals("data=fatcat", controller.action().response());
    }
}
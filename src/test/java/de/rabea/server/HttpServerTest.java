package de.rabea.server;

import de.rabea.request.Directory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {
    private String directory;

    @Before
    public void setup() {
        directory = new Directory().forResource();
    }

    @Test
    public void returns200ForAGetRequest() {
        FakeNetwork fakeNetwork = new FakeNetwork("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(fakeNetwork, new ContentStorage());
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\n", fakeNetwork.returnedResponse);
    }

    @Test
    public void returnsFileContentInTheResponseBody() {
        FakeNetwork fakeNetwork = new FakeNetwork("GET /file.txt HTTP/1.1");
        HttpServer httpServer = new HttpServer(fakeNetwork, new ContentStorage());
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\nSome content", fakeNetwork.returnedResponse);
    }
}
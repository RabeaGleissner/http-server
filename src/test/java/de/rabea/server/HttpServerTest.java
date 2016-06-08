package de.rabea.server;

import de.rabea.TestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {
    private String directory;

    @Before
    public void setup() {
        directory = TestHelper.directory();
    }

    @Test
    public void returns200ForAGetRequestToEmptyDirectory() {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        httpServer.start("PUBLIC_DIR");

        assertEquals("HTTP/1.1 200 OK\n\n", networkStub.returnedResponse);
    }

    @Test
    public void returns200AndDirectoryContentsForGetRequest() {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        String file = "file.txt";
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\n" +
                "<a href=/" + file + ">" +
                 file + "</a>", networkStub.returnedResponse);
    }

    @Test
    public void returnsFileContentInTheResponseBody() {
        NetworkStub networkStub = new NetworkStub("GET /file.txt HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\nSome content", networkStub.returnedResponse);
    }

    @Test
    public void returns405ForRequestWithIllegalMethod() {
        NetworkStub networkStub = new NetworkStub("POST /file.txt HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        httpServer.start(directory);

        assertEquals("HTTP/1.1 405 Method Not Allowed\n\n", networkStub.returnedResponse);
    }
}
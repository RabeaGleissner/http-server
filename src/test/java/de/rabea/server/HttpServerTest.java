package de.rabea.server;

import de.rabea.TestHelper;
import de.rabea.server.exceptions.SocketException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {
    private String directory;

    @Before
    public void setup() {
        directory = TestHelper.directory();
    }

    @Test
    public void returns200ForAGetRequestToEmptyDirectory() throws IOException {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        httpServer.start("PUBLIC_DIR");

        assertEquals("HTTP/1.1 200 OK\n\n", networkStub.returnedResponse);
    }

    @Test
    public void returns200AndDirectoryContentsForGetRequest() throws IOException {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        String file = "file.txt";
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\n" +
                "<a href=/" + file + ">" +
                 file + "</a>", networkStub.returnedResponse);
    }

    @Test
    public void returnsFileContentInTheResponseBody() throws IOException {
        NetworkStub networkStub = new NetworkStub("GET /file.txt HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\nSome content", networkStub.returnedResponse);
    }

    @Test
    public void returns405ForRequestWithIllegalMethod() throws IOException {
        NetworkStub networkStub = new NetworkStub("POST /file.txt HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        httpServer.start(directory);

        assertEquals("HTTP/1.1 405 Method Not Allowed\n\n", networkStub.returnedResponse);
    }

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotCloseSocket() throws IOException {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1").throwsIOException();
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage());
        httpServer.start("PUBLIC_DIR");
    }
}
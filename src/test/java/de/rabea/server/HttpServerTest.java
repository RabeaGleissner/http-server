package de.rabea.server;

import de.rabea.exceptions.SocketException;
import de.rabea.request.Log;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class HttpServerTest {
    private String directory;
    private Log log;

    @Before
    public void setup() {
        directory = directory();
        log = new Log();
    }

    @Test
    public void returns200ForAGetRequestToEmptyDirectory() {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage(), log);
        httpServer.start("PUBLIC_DIR");

        assertEquals("HTTP/1.1 200 OK\n\n", networkStub.returnedResponse);
    }

    @Test
    public void returns200AndDirectoryContentsForGetRequest() {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage(), log);
        String file = "file.txt";
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\n" +
                "<a href=/" + file + ">" +
                 file + "</a>", networkStub.returnedResponse);
    }

    @Test
    public void returnsFileContentInTheResponseBody() {
        NetworkStub networkStub = new NetworkStub("GET /file.txt HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage(), log);
        httpServer.start(directory);

        assertEquals("HTTP/1.1 200 OK\n\nSome content", networkStub.returnedResponse);
    }

    @Test
    public void returns405ForRequestWithIllegalMethod() {
        NetworkStub networkStub = new NetworkStub("POST /file.txt HTTP/1.1");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage(), log);
        httpServer.start(directory);

        assertEquals("HTTP/1.1 405 Method Not Allowed\n\n", networkStub.returnedResponse);
    }

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotCloseSocket() {
        NetworkStub networkStub = new NetworkStub("GET / HTTP/1.1").throwsIOException();
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage(), log);
        httpServer.start("PUBLIC_DIR");
    }

    @Test
    public void returnsLogsForAuthorisedRequest() {
        NetworkStub networkStub = new NetworkStub ("GET /logs HTTP/1.1\n" +
                "Authorization: Basic YWRtaW46aHVudGVyMg==\n");
        HttpServer httpServer = new HttpServer(networkStub, new ContentStorage(), log);
        httpServer.start("PUBLIC_DIR");

        assertEquals("HTTP/1.1 200 OK\n\n" + "GET /logs HTTP/1.1\n", networkStub.returnedResponse);
    }
}
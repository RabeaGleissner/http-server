package de.rabea;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpServerTest {

    @Test
    public void returns200ForAGetRequest() {
        FakeNetwork fakeNetwork = new FakeNetwork("GET / HTTP/1.1");
        HttpServer httpServer = new HttpServer(fakeNetwork);
        httpServer.start();

        assertEquals("HTTP/1.0 200 OK", fakeNetwork.returnedResponse);
    }
}
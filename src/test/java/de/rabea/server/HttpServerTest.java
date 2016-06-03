package de.rabea.server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {

    @Test
    public void returns200ForAGetRequest() {
        FakeNetwork fakeNetwork = new FakeNetwork("GET / HTTP/1.1\n");
        HttpServer httpServer = new HttpServer(fakeNetwork, new ContentHolder());
        httpServer.start();

        assertEquals("HTTP/1.1 200 OK", fakeNetwork.returnedResponse);
    }
}
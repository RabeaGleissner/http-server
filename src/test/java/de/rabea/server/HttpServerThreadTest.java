package de.rabea.server;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpServerThreadTest {

    @Test
    public void createsNewHttpServerWhichReturnsResponse() {
        FakeNetwork fakeNetwork = new FakeNetwork("GET / HTTP/1.1");
        HttpServerThread thread = new HttpServerThread(fakeNetwork, new ContentStorage());
        thread.start("PUBLIC_DIR");

        assertEquals("HTTP/1.1 200 OK\n\n", fakeNetwork.returnedResponse);
    }
}
package de.rabea.server;

import de.rabea.request.Log;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpServerFactoryTest {
    @Test
    public void createsHttpServerInstance() {
        HttpServerFactory factory = new HttpServerFactory(new NetworkStub(), new ContentStorage(), new Log());
        assertTrue(factory.create() instanceof HttpServer);
    }
}
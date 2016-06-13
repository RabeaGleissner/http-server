package de.rabea.server;

import de.rabea.request.Log;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpServerRunnerTest {
    @Test
    public void createsAndStartsHttpServer() {
        HttpServerSpy serverSpy = new HttpServerSpy(new NetworkStub(""), new ContentStorage(),
                new Log());
        HttpServerSpyFactory spyFactory = new HttpServerSpyFactory(serverSpy);
        HttpServerRunner serverRunner = new HttpServerRunner(spyFactory, "DIR");
        serverRunner.run();
        assertTrue(serverSpy.serverStarted);
    }
}
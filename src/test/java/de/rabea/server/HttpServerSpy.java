package de.rabea.server;

import de.rabea.request.Log;

public class HttpServerSpy extends HttpServer {

    public boolean serverStarted = false;

    public HttpServerSpy(Connection connection, ContentStorage contentStorage, Log log) {
        super(connection, contentStorage, log);
    }

    @Override
    public void start(String directory) {
        serverStarted = true;
    }
}

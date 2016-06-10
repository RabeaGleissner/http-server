package de.rabea.server;

import de.rabea.request.Log;

public class HttpServerThread extends Thread {

    private final Connection connection;
    private final ContentStorage contentStorage;
    private final Log log;

    public HttpServerThread(Connection connection, ContentStorage contentStorage, Log log) {
        this.connection = connection;
        this.contentStorage = contentStorage;
        this.log = log;
    }

    public void start(String directory) {
        new HttpServer(connection, contentStorage, log).start(directory);
    }
}

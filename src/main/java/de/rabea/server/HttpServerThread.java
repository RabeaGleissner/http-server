package de.rabea.server;

public class HttpServerThread extends Thread {

    private final Connection connection;
    private final ContentStorage contentStorage;

    public HttpServerThread(Connection connection, ContentStorage contentStorage) {
        this.connection = connection;
        this.contentStorage = contentStorage;
    }

    public void start(String directory) {
        new HttpServer(connection, contentStorage).start(directory);
    }
}

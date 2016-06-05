package de.rabea.server;

public class HttpServerThread extends Thread {

    private final Network network;
    private final ContentStorage contentStorage;

    public HttpServerThread(Network network, ContentStorage contentStorage) {
        this.network = network;
        this.contentStorage = contentStorage;
    }

    public void start(String directory) {
        new HttpServer(network, contentStorage).start(directory);
    }
}

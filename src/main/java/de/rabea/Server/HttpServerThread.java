package de.rabea.server;

public class HttpServerThread extends Thread {

    private final Network network;
    private ContentHolder contentHolder;

    public HttpServerThread(Network network, ContentHolder contentHolder) {
        this.network = network;
        this.contentHolder = contentHolder;
    }

    public void start() {
        new HttpServer(network, contentHolder).start();
    }
}

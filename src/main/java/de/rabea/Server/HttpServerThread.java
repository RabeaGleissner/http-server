package de.rabea.server;

public class HttpServerThread extends Thread {

    private final Network network;

    public HttpServerThread(Network network) {
        this.network = network;
    }

    public void start() {
        new HttpServer(network, new ContentHolder()).start();
    }
}

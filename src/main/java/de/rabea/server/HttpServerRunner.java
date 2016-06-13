package de.rabea.server;

import de.rabea.request.Log;

public class HttpServerRunner implements Runnable {

    private Network network;
    private ContentStorage contentStorage;
    private Log log;
    private String directory;

    public HttpServerRunner(Network network, ContentStorage contentStorage,
                            Log log, String directory) {
        this.network = network;
        this.contentStorage = contentStorage;
        this.log = log;
        this.directory = directory;
    }

    public HttpServerRunner() {

    }

    @Override
    public void run() {
        new HttpServer(network, contentStorage, log).start(directory);
    }
}

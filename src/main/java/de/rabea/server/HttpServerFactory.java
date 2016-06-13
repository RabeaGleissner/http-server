package de.rabea.server;

import de.rabea.request.Log;

public class HttpServerFactory {

    private Connection network;
    private ContentStorage contentStorage;
    private Log log;

    public HttpServerFactory(Connection network, ContentStorage contentStorage, Log log) {
        this.network = network;
        this.contentStorage = contentStorage;
        this.log = log;
    }

    public HttpServer create() {
        return new HttpServer(network, contentStorage, log);
    }
}

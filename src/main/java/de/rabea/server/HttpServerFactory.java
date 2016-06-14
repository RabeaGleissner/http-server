package de.rabea.server;

import de.rabea.request.Log;

public class HttpServerFactory implements ServerFactory {

    private final Connection network;
    private final ContentStorage contentStorage;
    private final Log log;

    public HttpServerFactory(Connection network, ContentStorage contentStorage, Log log) {
        this.network = network;
        this.contentStorage = contentStorage;
        this.log = log;
    }

    public HttpServer create() {
        return new HttpServer(network, contentStorage, log);
    }
}

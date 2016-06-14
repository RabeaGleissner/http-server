package de.rabea.server;

public class HttpServerSpyFactory implements ServerFactory {

    private HttpServerSpy serverSpy;

    public HttpServerSpyFactory(HttpServerSpy serverSpy) {
        this.serverSpy = serverSpy;
    }

    @Override
    public HttpServer create() {
        return serverSpy;
    }
}

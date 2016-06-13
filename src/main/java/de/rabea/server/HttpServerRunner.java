package de.rabea.server;

public class HttpServerRunner implements Runnable {

    private String directory;
    private HttpServerFactory httpServerFactory;

    public HttpServerRunner(HttpServerFactory httpServerFactory,
                            String directory) {
        this.directory = directory;
        this.httpServerFactory = httpServerFactory;
    }

    @Override
    public void run() {
        httpServerFactory.create().start(directory);
    }
}

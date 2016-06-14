package de.rabea.server;

public class HttpServerRunner implements Runnable {

    private final String directory;
    private final ServerFactory serverFactory;

    public HttpServerRunner(ServerFactory serverFactory,
                            String directory) {
        this.directory = directory;
        this.serverFactory = serverFactory;
    }

    @Override
    public void run() {
       serverFactory.create().start(directory);
    }
}

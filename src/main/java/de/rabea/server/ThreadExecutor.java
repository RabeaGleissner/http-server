package de.rabea.server;

import de.rabea.request.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor implements Runnable {
    private final ServerSocket serverSocket;
    private String directory;
    private final ExecutorService pool;
    private ContentStorage contentStorage;
    private Log log;

    public ThreadExecutor(ServerSocket serverSocket, ContentStorage contentStorage, Log log, String directory) {
        this.serverSocket = serverSocket;
        this.contentStorage = contentStorage;
        this.log = log;
        this.directory = directory;
        this.pool = Executors.newFixedThreadPool(20);
    }

    @Override
    public void run() {
        while (true) {
            try {
                pool.execute(new HttpServerRunner(new Network(serverSocket.accept()), contentStorage, log, directory));
            } catch (IOException e) {
                e.printStackTrace();
                pool.shutdown();
            }
        }

    }

}

package de.rabea.server;

import de.rabea.exceptions.ServerSocketException;
import de.rabea.request.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class ThreadExecutor implements Runnable {
    private ExecutorService executorService;
    private int poolSize = 20;
    private ServerSocket serverSocket;
    private ContentStorage contentStorage;
    private Log log;
    private String directory;

    public ThreadExecutor(ExecutorServiceFactory executorServiceFactory, ServerSocket serverSocket,
                          ContentStorage contentStorage, Log log, String directory) {
        this.serverSocket = serverSocket;
        this.executorService = executorServiceFactory.create(poolSize);
        this.contentStorage = contentStorage;
        this.log = log;
        this.directory = directory;
    }

    @Override
    public void run() {
        while (true) {
            execute();
        }
    }

    public void execute() {
        try {
            executeServerRunnerInThread();
        } catch (IOException e) {
            shutdown();
            throw new ServerSocketException("Cannot create Socket" + e.getMessage());
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public void executeServerRunnerInThread() throws IOException {
        executorService.execute(new HttpServerRunner(new Network(serverSocket.accept()),
                contentStorage, log, directory));
    }
}

package de.rabea.server;

import de.rabea.request.Log;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiHttpServer {
    private final boolean listening = true;
    private final ServerSocket serverSocket;
    private final ContentStorage contentStorage;
    private final Log log;

    public MultiHttpServer(ServerSocket serverSocket, ContentStorage contentStorage, Log log) {
        this.serverSocket = serverSocket;
        this.contentStorage = contentStorage;
        this.log = log;
    }

    public void run(String directory) {
        while (listening) {
            createNewServerThread(directory);
        }
    }

    private void createNewServerThread(String directory) {
        try {
            new HttpServerThread(new Network(serverSocket.accept()), contentStorage, log).start(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package de.rabea.server;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiHttpServer {
    private final boolean listening = true;
    private final ServerSocket serverSocket;
    private ContentStorage contentStorage;

    public MultiHttpServer(ServerSocket serverSocket, ContentStorage contentStorage) {
        this.serverSocket = serverSocket;
        this.contentStorage = contentStorage;
    }

    public void run(String directory) {
        while (listening) {
            try {
                new HttpServerThread(new Network(serverSocket.accept()), contentStorage).start(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package de.rabea.server;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiHttpServer {
    private final boolean listening = true;
    private final ServerSocket serverSocket;
    private ContentHolder contentHolder;

    public MultiHttpServer(ServerSocket serverSocket, ContentHolder contentHolder) {
        this.serverSocket = serverSocket;
        this.contentHolder = contentHolder;
    }

    public void run() {
        while (listening) {
            try {
                new HttpServerThread(new Network(serverSocket.accept()), contentHolder).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

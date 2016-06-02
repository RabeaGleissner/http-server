package de.rabea.server;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiHttpServer {
    private final boolean listening = true;
    private final ServerSocket serverSocket;

    public MultiHttpServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() {
        while (listening) {
            try {
                new HttpServerThread(new Network(serverSocket.accept())).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

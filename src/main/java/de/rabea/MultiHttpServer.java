package de.rabea;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiHttpServer {
    private boolean listening = true;
    private ServerSocket serverSocket;

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
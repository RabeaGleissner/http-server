package de.rabea;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        int port;
        String directory;

        if (args.length == 4) {
            port = Integer.parseInt(args[1]);
            directory = args[3];
        } else {
            port = 5000;
            directory = "PUBLIC_DIR";
        }

        System.out.println("Port: " + port + "\nDirectory: " + directory);
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            HttpServer httpServer = new HttpServer(new Network(serverSocket.accept()), directory);
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package de.rabea;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> arguments = new Arguments(args).parse();
        String directory = arguments.get("directory");
        int port = Integer.parseInt(arguments.get("port"));

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

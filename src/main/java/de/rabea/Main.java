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
            MultiHttpServer multiHttpServer = new MultiHttpServer(serverSocket);
            multiHttpServer.run();

        } catch (IOException e) {
            System.out.println("Cannot connect to port: " + port);
            e.printStackTrace();
        }
    }
}

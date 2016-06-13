package de.rabea;

import de.rabea.request.Log;
import de.rabea.server.Arguments;
import de.rabea.server.ContentStorage;
import de.rabea.server.MultiHttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> arguments = new Arguments(args).parse();
        String directory = arguments.get("directory");
        int port = Integer.parseInt(arguments.get("port"));

        System.out.println("Port: " + port + "\nDirectory: " + directory);
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            MultiHttpServer multiHttpServer = new MultiHttpServer(serverSocket, new ContentStorage(), new Log());
            multiHttpServer.run(directory);

        } catch (IOException e) {
            System.out.println("Cannot connect to port: " + port);
            e.printStackTrace();
        }
    }
}

package de.rabea;

import de.rabea.request.Log;
import de.rabea.server.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> arguments = new Arguments(args).parse();
        String directory = arguments.get("directory");
        int port = Integer.parseInt(arguments.get("port"));

        System.out.println("Port: " + port + "\nDirectory: " + directory);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            ThreadExecutor threadExecutor = new ThreadExecutor(
                    new ThreadPoolExecutorServiceFactory(),
                    serverSocket,
                    new ContentStorage(),
                    new Log(),
                    directory);
            threadExecutor.run();
        } catch (IOException e) {
            System.out.println("Cannot connect to port: " + port);
            e.printStackTrace();
        }
    }
}

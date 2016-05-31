package de.rabea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public void start(int port, String directory) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = clientReader.readLine();
            System.out.println("message = " + message);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

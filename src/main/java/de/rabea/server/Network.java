package de.rabea.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Network implements Connection {

    private final Socket socket;
    private final BufferedReader clientInputReader;
    private final PrintWriter sender;

    public Network(Socket socket) {
        this.socket = socket;
        this.clientInputReader = createReader();
        this.sender = createSender();
    }

    public String read() {
        try {
            return clientInputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String message) {
        sender.println(message);
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader createReader() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PrintWriter createSender() {
        try {
            return new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

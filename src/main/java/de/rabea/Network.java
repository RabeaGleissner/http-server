package de.rabea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Network {

    private final Socket socket;
    private final BufferedReader clientInputReader;

    public Network(Socket socket) {
        this.socket = socket;
        this.clientInputReader = createReader();
    }

    private BufferedReader createReader() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String read() {
        try {
            return clientInputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

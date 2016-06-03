package de.rabea.server;

import de.rabea.request.InputParser;

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
        InputParser parser = new InputParser();
        String requestBuilder = "";
        String line;
        try {
            while ((line = clientInputReader.readLine()) != null) {
                requestBuilder += (line + "\n");

                if (parser.isOneLiner(line)) {
                    break;
                }
                if (isEmptyLine(line)) {
                    requestBuilder += readBody(parser, requestBuilder);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestBuilder;
    }

    private boolean isEmptyLine(String line) {
        return line.equals("");
    }

    private String readBody(InputParser parser, String requestBuilder) throws IOException {
        String charAccumulator = "";
        int character;
        int length = parser.contentLength(requestBuilder);

        for (int i = 0; i < length; i++) {
            character = clientInputReader.read();
            charAccumulator = charAccumulator + ((char) character);
        }
        return charAccumulator;
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

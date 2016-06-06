package de.rabea.server;

import de.rabea.request.InputParser;

import java.io.*;
import java.net.Socket;

public class Network implements Connection {

    private final Socket socket;
    private final BufferedReader clientInputReader;
    private final OutputStream sender;

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
        int length = parser.contentLength(requestBuilder);

        for (int i = 0; i < length; i++) {
            charAccumulator = charAccumulator + ((char) clientInputReader.read());
        }
        return charAccumulator;
    }

    public void write(String head, byte[] body) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            sender.write(combinedHeadAndBody(head, body, out));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] combinedHeadAndBody(String head, byte[] body, ByteArrayOutputStream out) throws IOException {
        out.write(head.getBytes());
        out.write(body);
        return out.toByteArray();
    }

    private BufferedReader createReader() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OutputStream createSender() {
        try {
            return new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

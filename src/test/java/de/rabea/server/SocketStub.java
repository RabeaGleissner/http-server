package de.rabea.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class SocketStub extends Socket {

    private final String message;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    public SocketStub(String message) {
        this.message = message;
    }
    public SocketStub() {
        this.message = "";
    }

    @Override
    public InputStream getInputStream() {
         inputStream = new ByteArrayInputStream(message.getBytes());
        return inputStream;
    }

    @Override
    public ByteArrayOutputStream getOutputStream() {
        outputStream = new ByteArrayOutputStream();
        return outputStream;
    }

    public String messageSent() {
        return outputStream.toString();
    }
}
package de.rabea.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class FakeSocket extends Socket {

    private final String message;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    public FakeSocket(String message) {
        this.message = message;
    }
    public FakeSocket() {
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
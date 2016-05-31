package de.rabea;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.Socket;

public class FakeSocket extends Socket {

    private String message;

    public FakeSocket(String message) {
        this.message = message;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(message.getBytes());
    }
}

package de.rabea.server;

import java.io.IOException;

import static de.rabea.TestHelper.asString;

public class NetworkStub implements Connection {

    private String message;
    public String returnedResponse;
    private boolean throwException = false;


    public NetworkStub(String message) {
        this.message = message;
    }

    public NetworkStub throwsIOException() {
        return new NetworkStub("GET / HTTP/1.1", true);
    }

    public NetworkStub(String message, boolean throwException) {
        this.throwException = throwException;
        this.message = message;
    }

    public String read() {
       return message;
    }

    public void write(String response, byte[] body) {
        returnedResponse = response + asString(body);
    }

    public void close() throws IOException {
        if (throwException) {
            throw new IOException();
        }
    }
}

package de.rabea.server;

import java.io.IOException;

import static de.rabea.TestHelper.asString;

public class NetworkStub implements Connection {

    private final String message;
    private String exception = "";
    public String returnedResponse;

    public NetworkStub(String message) {
        this.message = message;
    }

    public NetworkStub(String message, String exception) {
        this.message = message;
        this.exception = exception;
    }

    public String read() {
       return message;
    }

    public void write(String response, byte[] body) {
        returnedResponse = response + asString(body);
    }

    public void close() throws IOException {
        if (exception.equals("throw exception")) {
            throw new IOException();
        }
    }
}

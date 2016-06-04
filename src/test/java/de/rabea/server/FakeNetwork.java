package de.rabea.server;

import de.rabea.Helper;

import static de.rabea.Helper.asString;

public class FakeNetwork implements Connection {

    private final String message;
    public String returnedResponse;

    public FakeNetwork(String message) {
        this.message = message;
    }

    public String read() {
       return message;
    }

    public void writeHeader(String response, byte[] body) {
        returnedResponse = response + asString(body);
    }

    @Override
    public void writeBody(byte[] body) {

    }

    public void close() {
    }
}

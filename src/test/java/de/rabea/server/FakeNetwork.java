package de.rabea.server;

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

    public void write(String response, byte[] body) {
        returnedResponse = response + asString(body);
    }

    public void close() {
    }
}

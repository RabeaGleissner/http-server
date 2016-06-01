package de.rabea;

public class FakeNetwork implements Connection {

    private final String message;
    public String returnedResponse;
    public boolean connectionClosed = false;

    public FakeNetwork(String message) {
        this.message = message;
    }

    public String read() {
       return message;
    }

    public void write(String response) {
        returnedResponse = response;
    }

    public void close() {
        connectionClosed = true;
    }
}

package de.rabea;

public class Response {

    private final String request;

    public Response(String requestMessage) {
        this.request = requestMessage;
    }

    public String generate() {
        return "HTTP/1.0 200 OK";
    }
}

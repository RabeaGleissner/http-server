package de.rabea.response;

import de.rabea.request.Request;

public class ResponseHead {

    private Request request;
    private final String PROTOCOL = "HTTP/1.1 ";

    public ResponseHead(Request request) {
        this.request = request;
    }

    public String generate() {
        return PROTOCOL + statusLine() + headers() + "\n";
    }

    private String headers() {
        return new Header(request).generate();
    }

    public String statusLine() {
        return new StatusLine(request).generate();
    }
}

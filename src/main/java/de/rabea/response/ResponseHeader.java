package de.rabea.response;

public class ResponseHeader {

    private final HttpResponse httpResponse;

    public ResponseHeader(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public String generate() {
        return httpResponse.create();
    }
}

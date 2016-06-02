package de.rabea.response;

public class ResponseGenerator {

    private final HttpResponse httpResponse;

    public ResponseGenerator(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public String generate() {
        return httpResponse.create();
    }
}

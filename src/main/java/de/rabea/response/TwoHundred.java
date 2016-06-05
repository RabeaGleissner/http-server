package de.rabea.response;

public class TwoHundred implements HttpResponse {

    public TwoHundred() {
    }

    public String create() {
        return "HTTP/1.1 200 OK\n\n";
    }
}

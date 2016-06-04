package de.rabea.response;

public class TwoHundred implements HttpResponse {

    public TwoHundred() {
    }

    public String create() {
        String response = "HTTP/1.1 200 OK\n\n";
        return response;
    }
}

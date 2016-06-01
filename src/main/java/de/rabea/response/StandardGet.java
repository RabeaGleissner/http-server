package de.rabea.response;

public class StandardGet implements HttpResponse {

    public String create() {
        return "HTTP/1.1 200 OK";
    }
}

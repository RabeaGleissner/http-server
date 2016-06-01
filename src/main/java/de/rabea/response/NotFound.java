package de.rabea.response;

public class NotFound implements HttpResponse {

    public String create() {
        return "HTTP/1.1 404 Not Found";
    }
}

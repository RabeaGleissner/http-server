package de.rabea.response;

public class Redirect implements HttpResponse {

    public String create() {
        return "HTTP/1.1 302 Found\nLocation: http://localhost:5000/";
    }
}

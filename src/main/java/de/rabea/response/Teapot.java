package de.rabea.response;

public class Teapot implements HttpResponse {

    public String create() {
        return "HTTP/1.1 418 I'm a teapot\n\nI'm a teapot";
    }
}

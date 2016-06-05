package de.rabea.response;

public class PartialContent implements HttpResponse {

    public PartialContent() {
    }

    @Override
    public String create() {
        return "HTTP/1.1 206 Partial Content\n\n";
    }
}

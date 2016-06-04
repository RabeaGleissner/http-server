package de.rabea.response;

public class PartialContent implements HttpResponse {

    public PartialContent() {
    }

    @Override
    public String create() {
        String response = "HTTP/1.1 206 Partial Content\n\n";
        return response;
    }
}

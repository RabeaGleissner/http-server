package de.rabea.response;

public class PartialContent implements HttpResponse {

    private final String responseBody;

    public PartialContent(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String create() {
        String response = "HTTP/1.1 206 Partial Content";
        if (!responseBody.equals("")) {
            response += ("\n\n" + responseBody);
        }
        return response;
    }
}

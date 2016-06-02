package de.rabea.response;

public class TwoHundred implements HttpResponse {

    private String responseBody;

    public TwoHundred(String responseBody) {
        this.responseBody = responseBody;
    }

    public String create() {
        String response = "HTTP/1.1 200 OK";
        if (responseBody != "") {
            response += ("\n\n" + responseBody);
        }
        return response;
    }
}

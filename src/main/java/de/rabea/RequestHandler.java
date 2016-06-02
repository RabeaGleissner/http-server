package de.rabea;

public class RequestHandler {

    private final String[] request;

    public RequestHandler(String incoming) {
        this.request = split(incoming);
    }

    public HttpVerb httpVerb() {
        return HttpVerb.convert(request[0]);
    }

    public String route() {
        return request[1];
    }

    private String[] split(String incoming) {
        return incoming.split(" ", -1);
    }
}

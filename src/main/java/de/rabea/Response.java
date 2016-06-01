package de.rabea;

import static de.rabea.HttpVerb.*;

public class Response {

    private final String route;
    private HttpVerb verb;
    private final String okResponse = "HTTP/1.0 200 OK";
    private final String notFoundResponse = "HTTP/1.0 404 Not Found";


    public Response(HttpVerb verb, String route) {
        this.verb = verb;
        this.route = route;
    }

    public String generate() {
        if (verb == GET && route.equals("/")) {
            return okResponse;
        } else {
            return notFoundResponse;
        }
    }
}

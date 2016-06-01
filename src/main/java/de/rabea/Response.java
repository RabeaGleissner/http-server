package de.rabea;

import static de.rabea.HttpVerb.*;

public class Response {

    private final String route;
    private final Routes routes;
    private final HttpVerb verb;
    private final String okResponse = "HTTP/1.0 200 OK";
    private final String notFoundResponse = "HTTP/1.0 404 Not Found";
    private final String newLine = "\n";

    public Response(HttpVerb verb, String route) {
        this.verb = verb;
        this.route = route;
        this.routes = new Routes();
    }

    public String generate() {
        if (verb == OPTIONS) {
           return (okResponse + newLine + new OptionsResponse(route).generate());
        }
        if (HttpVerb.isExisting(verb) && routes.isExisting(route)) {
            return okResponse;
        } else {
            return notFoundResponse;
        }
    }
}

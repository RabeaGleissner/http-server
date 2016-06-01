package de.rabea.response;

import de.rabea.HttpVerb;
import de.rabea.server.Routes;

import static de.rabea.HttpVerb.*;

public class Response {

    private final String route;
    private final Routes routes;
    private final HttpVerb verb;
    private String protocol = "HTTP/1.1 ";
    private final String okResponse = protocol + "200 OK";
    private final String notFoundResponse = protocol + "404 Not Found";
    private final String teapotResponse = protocol + "418 I'm a teapot\n\nI'm a teapot";
    private final String newLine = "\n";

    public Response(HttpVerb verb, String route) {
        this.verb = verb;
        this.route = route;
        this.routes = new Routes();
    }

    public String generate() {
        if (routes.coffeeRoute(route)) {
            return teapotResponse;
        }
        if (verb == OPTIONS) {
           return (okResponse + newLine + new OptionsResponse(route).generate());
        }
        if (routes.isExisting(route)) {
            return okResponse;
        } else {
            return notFoundResponse;
        }
    }
}

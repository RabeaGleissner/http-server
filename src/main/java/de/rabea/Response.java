package de.rabea;

public class Response {

    private final String route;
    private HttpVerb verb;
    private final String okResponse = "HTTP/1.0 200 OK";
    private final String notFoundResponse = "HTTP/1.0 404 Not Found";
    private final Routes routes;

    public Response(HttpVerb verb, String route) {
        this.verb = verb;
        this.route = route;
        this.routes = new Routes();
    }

    public String generate() {
        if (HttpVerb.isExisting(verb) && routes.isExisting(route)) {
            return okResponse;
        } else {
            return notFoundResponse;
        }
    }
}

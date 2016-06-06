package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.Route;

import static de.rabea.server.HttpVerb.OPTIONS;

public class ResponseHead {

    private Route route;
    private Request request;
    private String directory;
    private final String PROTOCOL = "HTTP/1.1 ";
    private final String OK = "200 OK\n";
    private final String TEAPOT = "418 I'm a teapot\n";
    private final String TEAPOT_HEADER = "\nI'm a teapot\n";
    private final String NOT_FOUND = "404 Not Found\n";
    private final String PARTIAL = "206 Partial Content\n";
    private final String REDIRECT = "302 Found\n";
    private final String LOCATION = "Location: http://localhost:5000/\n";

    public ResponseHead(Request request, String directory) {
        this.request = request;
        this.directory = directory;
        this.route = new Route();
    }

    public String generate() {
        return PROTOCOL + statusLine() + headers() + "\n";
    }

    private String headers() {
        if (isRedirect()) {
            return LOCATION;
        }
        if (isTeapot()) {
            return TEAPOT_HEADER;
        }

        if (request.httpVerb() == OPTIONS) {
            return "Allow: " + new Route().optionsFor(request.route()) + "\n";
        } else {
            return "";
        }
    }

    public String statusLine() {
        if (isRedirect()) {
            return REDIRECT;
        }

        if (isTeapot()) {
            return TEAPOT;
        }

        if (request.isPartial()) {
            return PARTIAL;
        }

        if (route.isExisting(request.route(), directory)) {
            return OK;
        } else {
            return NOT_FOUND;
        }
    }

    private boolean isTeapot() {
        return route.isTeaRoute(request.route());
    }

    private boolean isRedirect() {
        return route.isRedirect(request.route());
    }
}

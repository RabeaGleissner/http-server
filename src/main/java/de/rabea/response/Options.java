package de.rabea.response;

import de.rabea.server.Route;

public class Options implements HttpResponse {

    private final String route;

    public Options(String route) {
        this.route = route;
    }

    public String create() {
        return "HTTP/1.1 200 OK\nAllow: " + new Route().optionsFor(route);
    }
}

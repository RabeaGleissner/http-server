package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.Router;

import static de.rabea.server.HttpVerb.OPTIONS;

public class Header {

    private final Request request;
    private final String TEAPOT_HEADER = "\nI'm a teapot\n";
    private final String LOCATION = "Location: http://localhost:5000/\n";

    public Header(Request request) {
        this.request = request;
    }

    public String generate() {
        if (request.isRedirect()) {
            return LOCATION;
        }

        if (request.isTeapot()) {
            return TEAPOT_HEADER;
        }

        if (request.httpVerb == OPTIONS) {
            return "Allow: " + new Router(request.directory).optionsFor(request.route) + "\n";
        } else {
            return "";
        }
    }
}

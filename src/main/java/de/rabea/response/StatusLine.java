package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.Router;

public class StatusLine {

    private Request request;
    private String directory;
    private final String REDIRECT = "302 Found\n";
    private final String TEAPOT = "418 I'm a teapot\n";
    private final String PARTIAL = "206 Partial Content\n";
    private final String OK = "200 OK\n";
    private final String NOT_FOUND = "404 Not Found\n";

    public StatusLine(Request request, String directory) {
        this.request = request;
        this.directory = directory;
    }

    public String generate() {
        if (request.isRedirect()) {
            return REDIRECT;
        }

        if (request.isTeapot()) {
            return TEAPOT;
        }

        if (request.requestsPartialContent()) {
            return PARTIAL;
        }

        if (new Router().isExisting(request.route, directory)) {
            return OK;
        } else {
            return NOT_FOUND;
        }

    }
}

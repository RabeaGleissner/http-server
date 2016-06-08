package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.Request;
import de.rabea.server.Router;

public class StatusLine {

    private Request request;
    private Directory directory;
    private final String REDIRECT = "302 Found\n";
    private final String TEAPOT = "418 I'm a teapot\n";
    private final String PARTIAL = "206 Partial Content\n";
    private final String OK = "200 OK\n";
    private final String NOT_FOUND = "404 Not Found\n";

    public StatusLine(Request request, Directory directory) {
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

        if (new Router(directory).isExisting(request.route)) {
            return OK;
        } else {
            return NOT_FOUND;
        }

    }
}

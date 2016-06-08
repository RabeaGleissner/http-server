package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.Router;

public class StatusLine {

    private Request request;
    private Router router;
    private final String REDIRECT = "302 Found\n";
    private final String TEAPOT = "418 I'm a teapot\n";
    private final String PARTIAL = "206 Partial Content\n";
    private final String OK = "200 OK\n";
    private final String NOT_FOUND = "404 Not Found\n";
    private final String NOT_ALLOWED = "405 Method Not Allowed\n";
    private final String INTERNAL_SERVER_ERROR = "500 Internal Server Error\n";
    private final String UNAUTHORIZED = "401 Found\nWWW-Authenticate: Basic realm=\"Server logs\"\n";

    public StatusLine(Request request) {
        this.request = request;
        this.router = new Router(request.directory);
    }

    public String generate() {
        if (request.isRedirect()) {
            return REDIRECT;
        }

        if (request.isTeapot()) {
            return TEAPOT;
        }

        if (!request.knownUri()) {
            return NOT_FOUND;
        }

        if (methodNotAllowed()) {
            return NOT_ALLOWED;
        }

        if (router.routeNeedsAuthorisation(request.uri) && request.notAuthorised()) {
            return UNAUTHORIZED;
        }

        if (request.requestsPartialContent()) {
            return PARTIAL;
        }

        if (request.knownUri() || request.isAuthorised()) {
            return OK;
        } else {
            return INTERNAL_SERVER_ERROR;
        }
    }

    private boolean methodNotAllowed() {
        return !router.validMethod(request.httpVerb, request.uri);
    }
}

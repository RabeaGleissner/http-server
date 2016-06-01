package de.rabea.response;

import de.rabea.HttpVerb;
import de.rabea.server.Routes;

import static de.rabea.HttpVerb.*;

public class ResponseFactory {

    private final HttpVerb verb;
    private final String route;
    private final Routes routes;

    public ResponseFactory(HttpVerb verb, String route) {
        this.verb = verb;
        this.route = route;
        this.routes = new Routes();
    }

    public HttpResponse create() {
        if (routes.isRedirect(route)) {
            return new Redirect();
        } else if (routes.isTeaRoute(route)) {
            return new Teapot();
        } else if (verb == OPTIONS) {
            return new Options(route);
        } else if (routes.isExisting(route)) {
            return new StandardGet();
        } else {
            return new NotFound();
        }
    }
}

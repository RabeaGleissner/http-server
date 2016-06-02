package de.rabea.response;

import de.rabea.HttpVerb;
import de.rabea.server.Routes;

import static de.rabea.HttpVerb.OPTIONS;

public class ResponseFactory {

    private final HttpVerb verb;
    private final String route;
    private String requestBody;
    private final Routes routes;

    public ResponseFactory(HttpVerb verb, String route, String requestBody) {
        this.verb = verb;
        this.route = route;
        this.requestBody = requestBody;
        this.routes = new Routes();
    }

    public HttpResponse create() {
        if (routes.isRedirect(route)) {
            return new Redirect();
        }

        if (routes.isTeaRoute(route)) {
            return new FourEighteen();
        }

        if (verb == OPTIONS) {
            return new Options(route);
        }

        if (routes.isExisting(route)) {
            return new TwoHundred(requestBody);
        } else {
            return new NotFound();
        }
    }
}

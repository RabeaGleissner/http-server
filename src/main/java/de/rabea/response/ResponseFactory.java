package de.rabea.response;

import de.rabea.server.HttpVerb;
import de.rabea.server.Resource;

import static de.rabea.server.HttpVerb.OPTIONS;

public class ResponseFactory {

    private final HttpVerb verb;
    private final String route;
    private String requestBody;
    private String directory;
    private final Resource resource;

    public ResponseFactory(HttpVerb verb, String route, String requestBody, String directory) {
        this.verb = verb;
        this.route = route;
        this.requestBody = requestBody;
        this.directory = directory;
        this.resource = new Resource();
    }

    public HttpResponse create() {
        if (resource.isRedirect(route)) {
            return new Redirect();
        }

        if (resource.isTeaRoute(route)) {
            return new FourEighteen();
        }

        if (verb == OPTIONS) {
            return new Options(route);
        }

        if (resource.isExisting(route, directory)) {
            return new TwoHundred(requestBody);
        } else {
            return new NotFound();
        }
    }
}

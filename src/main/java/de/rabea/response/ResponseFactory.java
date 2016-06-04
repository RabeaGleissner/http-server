package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.HttpVerb;
import de.rabea.server.Resource;

import static de.rabea.server.HttpVerb.OPTIONS;

public class ResponseFactory {

    private final HttpVerb verb;
    private final String route;
    private final boolean partialContent;
    private final String directory;
    private final Resource resource;

    public ResponseFactory(Request request, String route, String directory) {
        this.verb = request.httpVerb();
        this.route = route;
        this.directory = directory;
        this.partialContent = request.isPartial();
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

        if (partialContent) {
           return new PartialContent();
        }

        if (resource.isExisting(route, directory)) {
            return new TwoHundred();
        } else {
            return new NotFound();
        }
    }
}

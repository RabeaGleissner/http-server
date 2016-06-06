package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.HttpVerb;
import de.rabea.server.Route;

import static de.rabea.server.HttpVerb.OPTIONS;

public class ResponseFactory {

    private final HttpVerb verb;
    private final String url;
    private final boolean partialContent;
    private final String directory;
    private final Route route;

    public ResponseFactory(Request request, String directory) {
        this.verb = request.httpVerb();
        this.url = request.route();
        this.directory = directory;
        this.partialContent = request.isPartial();
        this.route = new Route();
    }

    public HttpResponse create() {
        if (route.isRedirect(url)) {
            return new Redirect();
        }

        if (route.isTeaRoute(url)) {
            return new FourEighteen();
        }

        if (verb == OPTIONS) {
            return new Options(url);
        }

        if (partialContent) {
           return new PartialContent();
        }

        if (route.isExisting(url, directory)) {
            return new TwoHundred();
        } else {
            return new NotFound();
        }
    }
}

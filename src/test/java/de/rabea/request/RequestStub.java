package de.rabea.request;

import de.rabea.server.ContentStorage;
import de.rabea.server.HttpVerb;

public class RequestStub extends Request {

    private HttpVerb verb;
    private boolean partial;

    public RequestStub(String incoming, ContentStorage contentStorage, String directory) {
        super(incoming, contentStorage, directory);
    }

    public RequestStub(HttpVerb verb) {
        this.verb = verb;
        this.partial = false;
    }

    public RequestStub(HttpVerb verb, boolean partial) {
        this.verb = verb;
        this.partial = partial;
    }

    @Override
    public boolean isPartial() {
        return partial;
    }

    @Override
    public HttpVerb httpVerb() {
        return verb;
    }
}

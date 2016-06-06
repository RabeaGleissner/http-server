package de.rabea.request;

import de.rabea.server.HttpVerb;

public class RequestStub extends Request {

    private final String url;
    private final HttpVerb verb;
    private final boolean partial;

    public RequestStub(HttpVerb verb, String url) {
        this.url = url;
        this.verb = verb;
        this.partial = false;
    }

    public RequestStub(HttpVerb verb, String url, boolean partial) {
        this.verb = verb;
        this.partial = partial;
        this.url = url;
    }

    @Override
    public String route() {
        return url;
    }

    @Override
    public String url() {
       return url;
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

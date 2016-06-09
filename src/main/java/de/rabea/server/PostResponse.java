package de.rabea.server;

import de.rabea.request.Request;

public class PostResponse implements Action {

    private Request request;

    public PostResponse(Request request) {
        this.request = request;
    }

    @Override
    public String response() {
        if (request.hasBody()) {
            return request.body;
        } else if (request.hasUrlParams()) {
            return request.urlParams;
        } else {
            return "";
        }
    }
}

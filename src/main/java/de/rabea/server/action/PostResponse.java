package de.rabea.server.action;

import de.rabea.request.Request;
import de.rabea.server.Action;

public class PostResponse implements Action {

    private Request request;

    public PostResponse(Request request) {
        this.request = request;
    }

    @Override
    public String response() {
        if (request.hasBody()) {
            return request.body;
        } else {
            return "";
        }
    }
}

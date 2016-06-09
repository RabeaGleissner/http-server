package de.rabea.server.action;

import de.rabea.request.Request;
import de.rabea.server.Action;

public class Params implements Action {

    private Request request;

    public Params(Request request) {
        this.request = request;
    }

    @Override
    public String response() {
        if (request.hasUrlParams()) {
            return request.urlParams;
        } else {
            return "";
        }
    }
}

package de.rabea.server;

import de.rabea.request.Log;
import de.rabea.request.Request;

public class ActionCreator {

    private Request request;
    private Log log;

    public ActionCreator(Request request, Log log) {
        this.request = request;
        this.log = log;
    }

    public Action create() {
        if (request.asksForLogs()) {
            return new GetLogs(log);
        } else {
            return new EmptyResponse();
        }
    }
}

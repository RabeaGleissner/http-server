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
        if (requestForLogs()) {
            return new GetLogs(log);
        } else if (postRequest()) {
            return new PostResponse(request);
        } else {
            return new EmptyResponse();
        }
    }

    private boolean requestForLogs() {
        return request.asksForLogs();
    }

    private boolean postRequest() {
        return request.httpVerb == HttpVerb.POST;
    }
}

package de.rabea.server;

import de.rabea.request.Log;
import de.rabea.request.Request;
import de.rabea.server.action.EmptyResponse;
import de.rabea.server.action.GetLogs;
import de.rabea.server.action.Params;
import de.rabea.server.action.PostResponse;

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
        } else if (postRequest() ||requestToForms()) {
            return new PostResponse(request);
        } else if (requestWithUrlParams()) {
            return new Params(request);
        } else {
            return new EmptyResponse();
        }
    }

    private boolean requestToForms() {
        return request.route.equals("/form");
    }

    private boolean requestWithUrlParams() {
        return request.hasUrlParams();
    }

    private boolean requestForLogs() {
        return request.asksForLogs();
    }

    private boolean postRequest() {
        return request.httpVerb == HttpVerb.POST;
    }
}

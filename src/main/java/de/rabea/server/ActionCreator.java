package de.rabea.server;

import de.rabea.request.Log;
import de.rabea.request.Request;
import de.rabea.server.action.*;

public class ActionCreator {

    private Request request;
    private Log log;
    private Router router;

    public ActionCreator(Request request, Log log) {
        this.request = request;
        this.log = log;
        this.router = new Router(request.directory);
    }

    public Action create() {
        if (requestForLogs()) {
            return new GetLogs(log);
        } else if (postRequest() ||requestToForms()) {
            return new PostResponse(request);
        } else if (requestWithUrlParams()) {
            return new Params(request);
        } else if (showDirectoryContents()) {
            return new DirectoryContent(request.directory);
        } else {
            return new EmptyResponse();
        }
    }

    private boolean directoryHasContent() {
        return request.directory.containsContent();
    }

    private String fileName(String file) {
        String[] folders = file.split("/");
        return folders[folders.length - 1];
    }

    private boolean requestMethodAllowed() {
        return router.validMethod(request.httpVerb, request.uri);
    }

    private boolean showDirectoryContents() {
        return requestedRootRoute() && directoryHasContent();
    }

    private boolean requestedRootRoute() {
        return router.requestRoot(request.route);
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

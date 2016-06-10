package de.rabea.response;

import de.rabea.request.Log;
import de.rabea.request.Request;
import de.rabea.response.body.*;
import de.rabea.server.HttpVerb;
import de.rabea.server.Router;

public class ResponseBodyCreator {

    private Request request;
    private Log log;
    private Router router;

    public ResponseBodyCreator(Request request, Log log) {
        this.request = request;
        this.log = log;
        this.router = new Router(request.directory);
    }

    public ResponseBody create() {
        if (requestForLogs()) {
            return new Logs(log);
        } else if (postRequest() ||requestToForms()) {
            return new PostResponse(request);
        } else if (requestWithUrlParams()) {
            return new Params(request);
        } else if (showDirectoryContents()) {
            return new DirectoryContent(request.directory);
        } else if (directoryHasContent() && requestMethodAllowed()) {
            return new FileContent(request.route, request.range,
                    request.directory, request.requestsPartialContent());
        } else {
            return new EmptyResponse();
        }
    }

    private boolean directoryHasContent() {
        return request.directory.containsContent();
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

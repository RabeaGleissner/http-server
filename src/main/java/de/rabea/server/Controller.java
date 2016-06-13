package de.rabea.server;

import de.rabea.request.Log;
import de.rabea.request.Request;
import de.rabea.response.ResponseBody;
import de.rabea.response.ResponseBodyCreator;

public class Controller {

    private final Request request;
    private final Log log;

    public Controller(Request request, Log log) {
        this.request = request;
        this.log = log;
        logAllIncoming(request, log);
    }

    public ResponseBody action() {
        return new ResponseBodyCreator(request, log).create();
    }

    private void logAllIncoming(Request request, Log log) {
        log.register(request.head());
    }


}

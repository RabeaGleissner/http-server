package de.rabea.server;

import de.rabea.request.Log;
import de.rabea.request.Request;

public class Controller {

    private Request request;
    private Log log;

    public Controller(Request request, Log log) {
        this.request = request;
        this.log = log;
        logAllIncoming(request, log);
    }

    public Action action() {
        return new ActionCreator(request, log).create();
    }

    private void logAllIncoming(Request request, Log log) {
        log.register(request.head());
    }


}

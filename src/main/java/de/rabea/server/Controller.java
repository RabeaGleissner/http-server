package de.rabea.server;

import de.rabea.request.Log;
import de.rabea.request.Request;

public class Controller {

    private Request request;
    private Log log;

    public Controller(Request request, Log log) {
        this.request = request;
        this.log = log;
        log.register(request.head());
    }

    public String response() {
        if (request.asksForLogs()) {
            return log.show();
        } else {
            return "";
        }
    }
}

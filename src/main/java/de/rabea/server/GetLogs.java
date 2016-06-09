package de.rabea.server;

import de.rabea.request.Log;

public class GetLogs implements Action {

    private Log log;

    public GetLogs(Log log) {
        this.log = log;
    }

    @Override
    public String response() {
        return log.show();
    }
}

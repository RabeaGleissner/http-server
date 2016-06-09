package de.rabea.server.action;

import de.rabea.request.Log;
import de.rabea.server.Action;

public class GetLogs implements Action {

    private Log log;

    public GetLogs(Log log) {
        this.log = log;
    }

    @Override
    public byte[] response() {
        return log.show().getBytes();
    }
}

package de.rabea.response.body;

import de.rabea.request.Log;
import de.rabea.server.ResponseBody;

public class GetLogs implements ResponseBody {

    private Log log;

    public GetLogs(Log log) {
        this.log = log;
    }

    @Override
    public byte[] response() {
        return log.show().getBytes();
    }
}

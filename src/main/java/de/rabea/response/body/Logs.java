package de.rabea.response.body;

import de.rabea.request.Log;
import de.rabea.response.ResponseBody;

public class Logs implements ResponseBody {

    private final Log log;

    public Logs(Log log) {
        this.log = log;
    }

    @Override
    public byte[] response() {
        return log.show().getBytes();
    }
}

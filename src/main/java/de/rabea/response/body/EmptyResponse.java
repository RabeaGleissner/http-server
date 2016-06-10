package de.rabea.response.body;

import de.rabea.server.ResponseBody;

public class EmptyResponse implements ResponseBody {

    @Override
    public byte[] response() {
        return "".getBytes();
    }
}

package de.rabea.response.body;

import de.rabea.response.ResponseBody;

public class EmptyResponse implements ResponseBody {

    @Override
    public byte[] response() {
        return "".getBytes();
    }
}

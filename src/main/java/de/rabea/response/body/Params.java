package de.rabea.response.body;

import de.rabea.request.Request;
import de.rabea.response.ResponseBody;

public class Params implements ResponseBody {

    private final Request request;

    public Params(Request request) {
        this.request = request;
    }

    @Override
    public byte[] response() {
        return paramsAsBody().getBytes();
    }

    private String paramsAsBody() {
        if (request.hasUrlParams()) {
            return request.urlParams;
        } else {
            return "";
        }
    }
}

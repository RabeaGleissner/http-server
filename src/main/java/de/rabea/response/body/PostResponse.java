package de.rabea.response.body;

import de.rabea.request.Request;
import de.rabea.response.ResponseBody;

public class PostResponse implements ResponseBody {

    private final Request request;

    public PostResponse(Request request) {
        this.request = request;
    }

    @Override
    public byte[] response() {
        return returnBody().getBytes();
    }

    private String returnBody() {
        if (request.hasBody()) {
            return request.body;
        } else {
            return "";
        }
    }
}

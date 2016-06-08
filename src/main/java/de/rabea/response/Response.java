package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.ContentStorage;

public class Response {

    private Request request;
    private ContentStorage contentStorage;

    public Response(Request request, ContentStorage contentStorage) {
        this.request = request;
        this.contentStorage = contentStorage;
    }

    public byte[] body() {
        return contentStorage.bodyFor(request.route);
    }

    public String head() {
        ResponseHead responseHead = new ResponseHead(request);
        return responseHead.generate();
    }
}

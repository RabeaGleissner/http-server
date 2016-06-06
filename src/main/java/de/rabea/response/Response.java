package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.ContentStorage;

public class Response {

    private Request request;
    private String directory;
    private ContentStorage contentStorage;

    public Response(Request request, String directory, ContentStorage contentStorage) {
        this.request = request;
        this.directory = directory;
        this.contentStorage = contentStorage;
    }

    public byte[] body() {
        return contentStorage.bodyFor(request.route());
    }

    public String head() {
        ResponseHead responseHead = new ResponseHead(request, directory);
        return responseHead.generate();
    }
}

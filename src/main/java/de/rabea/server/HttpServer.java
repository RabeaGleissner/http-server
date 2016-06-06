package de.rabea.server;

import de.rabea.request.Request;
import de.rabea.response.Response;
import de.rabea.response.ResponseBody;

public class HttpServer {

    private final Connection connection;
    private final ContentStorage contentStorage;

    public HttpServer(Connection connection, ContentStorage contentStorage) {
        this.connection = connection;
        this.contentStorage = contentStorage;
    }

    public void start(String directory) {
        Request request = handleIncoming(directory, connection.read());
        Response response = new Response(request, directory, contentStorage);
        System.out.println(response.header());
        connection.write(response.header(), response.body());
        connection.close();
    }

    private Request handleIncoming(String directory, String incoming) {
        Request request = new Request(incoming);
        contentStorage.update(request.route(), responseBody(directory, request), request.httpVerb());
        return request;
    }

    private byte[] responseBody(String directory, Request request) {
        return new ResponseBody(request, directory).create();
    }
}

package de.rabea.server;

import de.rabea.request.Directory;
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
        Response response = new Response(request, contentStorage);
        connection.write(response.head(), response.body());
        connection.close();
    }

    private Request handleIncoming(String directoryPath, String incoming) {
        Request request = new Request(incoming, new Directory(directoryPath));
        contentStorage.update(request.route, responseBody(request), request.httpVerb);
        return request;
    }

    private byte[] responseBody(Request request) {
        return new ResponseBody(request).create();
    }
}

package de.rabea.server;

import de.rabea.request.Request;
import de.rabea.response.ResponseBody;
import de.rabea.response.ResponseFactory;
import de.rabea.response.ResponseHeader;

public class HttpServer {

    private final Connection connection;
    private final ContentStorage contentStorage;

    public HttpServer(Connection connection, ContentStorage contentStorage) {
        this.connection = connection;
        this.contentStorage = contentStorage;
    }

    public void start(String directory) {
        String incoming = connection.read();
        System.out.println("incoming = " + incoming);
        Request request = handleIncoming(directory, incoming);

        String route = request.route();

        ResponseHeader responseHeader = new ResponseHeader(
                new ResponseFactory(
                        request,
                        route,
                        directory).create());
        connection.write(responseHeader.generate(), contentStorage.bodyFor(route));
        connection.close();
    }

    private Request handleIncoming(String directory, String incoming) {
        Request request = new Request(incoming);
        byte[] responseBody = new ResponseBody(request, directory).create();
        contentStorage.update(request.route(), responseBody, request.httpVerb());
        return request;
    }
}

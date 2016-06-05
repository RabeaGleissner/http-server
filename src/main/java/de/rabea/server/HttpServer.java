package de.rabea.server;

import de.rabea.request.Request;
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
        Request request = new Request(incoming, contentStorage, directory);

        String route = request.route();

        ResponseHeader responseHeader = new ResponseHeader(
                new ResponseFactory(
                        request,
                        route,
                        directory).create());
        System.out.println(new String(contentStorage.bodyFor(route)));
        connection.write(responseHeader.generate(), contentStorage.bodyFor(route));
        connection.close();
    }
}

package de.rabea.server;

import de.rabea.request.Request;
import de.rabea.response.ResponseFactory;
import de.rabea.response.ResponseGenerator;

public class HttpServer {

    private final Connection connection;
    private ContentStorage contentStorage;

    public HttpServer(Connection connection, ContentStorage contentStorage) {
        this.connection = connection;
        this.contentStorage = contentStorage;
    }

    public void start() {
        String incoming = connection.read();
        Request request = new Request(incoming, contentStorage);
        String route = request.route();

        ResponseGenerator responseGenerator = new ResponseGenerator(
                new ResponseFactory(
                        request.httpVerb(),
                        route,
                        contentStorage.getContentFor(route))
                        .create());
        connection.write(responseGenerator.generate());
        connection.close();
    }
}

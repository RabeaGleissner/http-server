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

    public void start(String directory) {
        String incoming = connection.read();
        System.out.println("incoming = " + incoming);
        Request request = new Request(incoming, contentStorage, directory);

        String route = request.route();

        ResponseGenerator responseGenerator = new ResponseGenerator(
                new ResponseFactory(
                        request,
                        route,
                        contentStorage.getContentFor(route),
                        directory)
                        .create());
        connection.write(responseGenerator.generate());
        System.out.println(responseGenerator.generate());
        connection.close();
    }
}

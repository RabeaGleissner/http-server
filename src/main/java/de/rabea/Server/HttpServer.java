package de.rabea.server;

import de.rabea.RequestHandler;
import de.rabea.response.ResponseFactory;
import de.rabea.response.ResponseGenerator;

public class HttpServer {

    private final Connection connection;
    private ContentHolder contentHolder;

    public HttpServer(Connection connection, ContentHolder contentHolder) {
        this.connection = connection;
        this.contentHolder = contentHolder;
    }

    public void start() {
        String incoming = connection.read();
        RequestHandler requestHandler = new RequestHandler(incoming);
        ResponseGenerator responseGenerator = new ResponseGenerator(
                new ResponseFactory(requestHandler.httpVerb(),
                        requestHandler.route(),
                        contentHolder.getContentFor(requestHandler.route())).create());
        connection.write(responseGenerator.generate());
        connection.close();
    }
}

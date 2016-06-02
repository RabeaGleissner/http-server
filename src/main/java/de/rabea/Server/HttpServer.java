package de.rabea.server;

import de.rabea.RequestHandler;
import de.rabea.response.ResponseFactory;
import de.rabea.response.ResponseGenerator;

public class HttpServer {

    private final Connection connection;

    public HttpServer(Connection connection) {
        this.connection = connection;
    }

    public void start() {
        String incoming = connection.read();
        RequestHandler requestHandler = new RequestHandler(incoming);
        ResponseGenerator responseGenerator = new ResponseGenerator(
                new ResponseFactory(requestHandler.httpVerb(), requestHandler.route()).create());
        connection.write(responseGenerator.generate());
        connection.close();
    }
}

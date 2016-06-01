package de.rabea.server;

import de.rabea.RequestHandler;
import de.rabea.response.ResponseGenerator;

public class HttpServer {

    private final Connection connection;

    public HttpServer(Connection connection) {
        this.connection = connection;
    }

    public void start() {
        String incoming = connection.read();
        System.out.println("request = " + incoming);
        RequestHandler requestHandler = new RequestHandler(incoming);
        ResponseGenerator responseGenerator = new ResponseGenerator(requestHandler.httpVerb(),
                requestHandler.route());
        connection.write(responseGenerator.generate());
        connection.close();
    }
}

package de.rabea.server;

import de.rabea.RequestHandler;
import de.rabea.response.Response;

public class HttpServer {

    private final Connection connection;

    public HttpServer(Connection connection) {
        this.connection = connection;
    }

    public void start() {
        String incoming = connection.read();
        System.out.println("request = " + incoming);
        RequestHandler requestHandler = new RequestHandler(incoming);
        Response response = new Response(requestHandler.httpVerb(),
                requestHandler.route());
        connection.write(response.generate());
        connection.close();
    }
}

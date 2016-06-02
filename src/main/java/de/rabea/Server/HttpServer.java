package de.rabea.server;

import de.rabea.HttpVerb;
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
        String route = requestHandler.route();

        updateContentStorage(incoming, requestHandler, route);

        ResponseGenerator responseGenerator = new ResponseGenerator(
                new ResponseFactory(requestHandler.httpVerb(),
                        route,
                        contentHolder.getContentFor(route)).create());

        connection.write(responseGenerator.generate());
        connection.close();
    }

    private void updateContentStorage(String incoming, RequestHandler requestHandler, String route) {
        if (new InputParser().hasBody(incoming)) {
            contentHolder.save(route, requestHandler.body());
        }

        if (requestHandler.httpVerb() == HttpVerb.DELETE) {
            contentHolder.deleteFor(route);
        }
    }
}

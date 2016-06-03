package de.rabea.server;

import de.rabea.HttpVerb;
import de.rabea.RequestSplitter;
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
        RequestSplitter requestSplitter = new RequestSplitter(incoming);
        String route = requestSplitter.route();

        updateContentStorage(incoming, requestSplitter);

        ResponseGenerator responseGenerator = new ResponseGenerator(
                new ResponseFactory(
                        requestSplitter.httpVerb(),
                        route,
                        contentHolder.getContentFor(route))
                        .create());

        connection.write(responseGenerator.generate());
        connection.close();
    }

    private void updateContentStorage(String incoming, RequestSplitter requestSplitter) {
        if (new InputParser().hasBody(incoming)) {
            contentHolder.save(requestSplitter.route(), requestSplitter.body());
        }

        if (requestSplitter.httpVerb() == HttpVerb.DELETE) {
            contentHolder.deleteFor(requestSplitter.route());
        }
    }
}

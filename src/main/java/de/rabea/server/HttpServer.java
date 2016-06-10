package de.rabea.server;

import de.rabea.exceptions.SocketException;
import de.rabea.request.Directory;
import de.rabea.request.Log;
import de.rabea.request.Request;
import de.rabea.response.Response;
import de.rabea.response.ResponseBody;

import java.io.IOException;

public class HttpServer {

    private final Connection connection;
    private final ContentStorage contentStorage;
    private Log log;

    private HttpServer(Connection connection, ContentStorage contentStorage) {
        this.connection = connection;
        this.contentStorage = contentStorage;
    }

    public HttpServer(Connection connection, ContentStorage contentStorage, Log log) {
        this(connection, contentStorage);
        this.log = log;
    }

    public void start(String directory) {
        Request request = handleIncoming(directory, connection.read());
        Response response = new Response(request, contentStorage);
        connection.write(response.head(), response.body());
        try {
            connection.close();
        } catch (IOException e) {
            throw new SocketException("Could not close: " + e.getMessage());
        }
    }

    public Request handleIncoming(String directoryPath, String incoming) {
        Request request = new Request(incoming, new Directory(directoryPath));
        contentStorage.update(request.route,
                responseBody(new Controller(request, log)),
                request.httpVerb);
        return request;
    }

    private byte[] responseBody(Controller controller) {
        ResponseBody responseBody = controller.action();
           return responseBody.response();
    }
}

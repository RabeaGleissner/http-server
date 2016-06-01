package de.rabea;

public class HttpServer {

    private Connection connection;

    public HttpServer(Connection connection) {
        this.connection = connection;
    }

    public void start() {
        String request = connection.read();
        System.out.println("request = " + request);
        Response response = new Response(request);
        connection.write(response.generate());
        connection.close();
    }
}

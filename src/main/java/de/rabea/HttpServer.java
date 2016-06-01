package de.rabea;

public class HttpServer {

    private Network network;
    private String directory;

    public HttpServer(Network network, String directory) {
        this.network = network;
        this.directory = directory;
    }

    public void start() {
        String message = network.read();
        Response response = new Response(message);
        network.write(response.generate());
    }
}

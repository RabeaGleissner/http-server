package de.rabea.server;

public interface Connection {
    String read();
    void write(String response, byte[] body);
    void close();
}

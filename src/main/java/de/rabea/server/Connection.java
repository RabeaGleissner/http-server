package de.rabea.server;

public interface Connection {
    String read();
    void writeHeader(String response, byte[] body);
    void close();
}

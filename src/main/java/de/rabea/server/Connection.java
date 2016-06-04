package de.rabea.server;

public interface Connection {
    String read();
    void writeHeader(String response);
    void writeBody(byte[] body);
    void close();
}

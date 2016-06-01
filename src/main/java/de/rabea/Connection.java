package de.rabea;

public interface Connection {
    String read();
    void write(String response);
    void close();
}

package de.rabea.server;

import java.io.IOException;

public interface Connection {
    String read();
    void write(String response, byte[] body);
    void close() throws IOException;
}

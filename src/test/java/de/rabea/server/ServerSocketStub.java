package de.rabea.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketStub extends ServerSocket {

    private static boolean throwException;

    public ServerSocketStub() throws IOException {
        this.throwException = false;
    }

    public ServerSocketStub(boolean throwException) throws IOException {
        super();
        this.throwException = throwException;
    }

    public static ServerSocket throwsException() throws IOException {
        throwException = true;
        return new ServerSocketStub(throwException);
    }

    @Override
    public Socket accept() throws IOException {
        if (throwException) {
            throw new IOException();
        } else {
            return new SocketStub();
        }
    }
}

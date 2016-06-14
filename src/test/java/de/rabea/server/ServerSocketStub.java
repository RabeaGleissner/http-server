package de.rabea.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketStub extends ServerSocket {

    private static boolean throwException;

    public ServerSocketStub() throws IOException {
        throwException = false;
    }

    private ServerSocketStub(boolean throwException) throws IOException {
        super();
        ServerSocketStub.throwException = throwException;
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

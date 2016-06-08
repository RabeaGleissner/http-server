package de.rabea.server;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NetworkTest {

    @Test
    public void readsOneLineOfInputForSimpleGETRequest() {
        String request = "GET/form HTTP/1.1\n";
        SocketStub socketStub = new SocketStub(request);
        Network network = new Network(socketStub);
        assertEquals("GET/form HTTP/1.1\n", network.read());
    }

    @Test
    public void readsBodyOfPostRequest() {
        String request = "POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat";
        Network network = new Network(new SocketStub(request));
        String[] parsedRequestLines = network.read().split("\n");
        String lastLine = parsedRequestLines[parsedRequestLines.length -1];
        assertEquals("data=fatcat", lastLine);
    }

    @Test
    public void readsPutRequestWithoutBody() {
        String request = "PUT /method_options HTTP/1.1\n" +
                "Content-Length: 0\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n";
        Network network = new Network(new SocketStub(request));
        assertEquals(request, network.read());
    }

    @Test
    public void sendsHeaderAndBodyToClient() {
        SocketStub socketStub = new SocketStub();
        Network network = new Network(socketStub);
        String message = "hello!";
        network.write(message, "body".getBytes());
        assertEquals("hello!body", socketStub.messageSent());
    }

    @Test
    public void closesSocket() throws IOException {
        SocketSpy socketSpy = new SocketSpy();
        Network network = new Network(socketSpy);
        network.close();
        assertTrue(socketSpy.socketWasClosed);
    }

    public class SocketSpy extends SocketStub {
        public boolean socketWasClosed = false;

        public void close() {
            socketWasClosed = true;
        }
    }

    @Test(expected = IOException.class)
    public void socketThrowsIOExceptionWhenItCannotClose() throws IOException {
        SocketWithException socket = new SocketWithException();
        Network network = new Network(socket);
        network.close();
    }

    public class SocketWithException extends Socket {

        @Override
        public void close() throws IOException {
            throw new IOException();
        }

        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream("".getBytes()) {
            };
        }

        @Override
        public OutputStream getOutputStream() {
            return new ByteArrayOutputStream();
        }
    }
}
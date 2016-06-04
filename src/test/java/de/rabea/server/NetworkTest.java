package de.rabea.server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NetworkTest {

    @Test
    public void readsOneLineInputForGETRequest() {
        String request = "GET/form HTTP/1.1\n";
        FakeSocket fakeSocket = new FakeSocket(request);
        Network network = new Network(fakeSocket);
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
        FakeSocket fakeSocket = new FakeSocket(request);
        Network network = new Network(fakeSocket);
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
        FakeSocket fakeSocket = new FakeSocket(request);
        Network network = new Network(fakeSocket);
        assertEquals(request, network.read());
    }

    @Test
    public void sendsHeaderToClient() {
        FakeSocket fakeSocket = new FakeSocket();
        Network network = new Network(fakeSocket);
        network.writeHeader("hello!", new byte[0]);
        assertEquals("hello!", fakeSocket.messageSent());
    }

    @Test
    public void sendsBodyToClient() {
        FakeSocket fakeSocket = new FakeSocket();
        Network network = new Network(fakeSocket);
        String message = "hello!";
        network.writeBody(message.getBytes());
        assertEquals("hello!", fakeSocket.messageSent());
    }

    @Test
    public void closesSocket() {
        SocketSpy socketSpy = new SocketSpy();
        Network network = new Network(socketSpy);
        network.close();
        assertTrue(socketSpy.socketWasClosed);
    }

    public class SocketSpy extends FakeSocket {
        public boolean socketWasClosed = false;

        public void close() {
            socketWasClosed = true;
        }
    }
}
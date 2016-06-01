package de.rabea;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NetworkTest {

    @Test
    public void readsInputFromClient() {
        FakeSocket fakeSocket = new FakeSocket("hello");
        Network network = new Network(fakeSocket);
        assertEquals("hello", network.read());
    }

    @Test
    public void sendsMessageToClient() {
        FakeSocket fakeSocket = new FakeSocket();
        Network network = new Network(fakeSocket);
        network.write("hello!");
        assertEquals("hello!\n", fakeSocket.messageSent());
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
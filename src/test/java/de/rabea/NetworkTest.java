package de.rabea;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NetworkTest {

    @Test
    public void readsInputFromClient() {
        FakeSocket fakeSocket = new FakeSocket("hello");
        Network network = new Network(fakeSocket);
        assertEquals("hello", network.read());
    }

    @Test
    public void sendsMessageToClient() {
        FakeSocket fakeSocket = new FakeSocket("");
        Network network = new Network(fakeSocket);
        network.write("hello!");
        assertEquals("hello!\n", fakeSocket.messageSent());
    }
}
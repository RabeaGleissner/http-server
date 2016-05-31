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
}
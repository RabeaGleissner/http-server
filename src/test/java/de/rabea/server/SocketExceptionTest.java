package de.rabea.server;

import org.junit.Test;

import static org.junit.Assert.*;

public class SocketExceptionTest {
    @Test
    public void takesAndReturnsMessage() {
        SocketException exception = new SocketException("message");
        assertEquals("message", exception.getMessage());
    }
}
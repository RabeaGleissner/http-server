package de.rabea.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SocketExceptionTest {
    @Test
    public void takesAndReturnsMessage() {
        SocketException exception = new SocketException("message");
        assertEquals("message", exception.getMessage());
    }
}
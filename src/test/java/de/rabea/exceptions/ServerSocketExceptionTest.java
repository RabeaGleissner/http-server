package de.rabea.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerSocketExceptionTest {

    @Test
    public void takesAndReturnsMessage() {
        ServerSocketException exception = new ServerSocketException("message");
        assertEquals("message", exception.getMessage());
    }
}
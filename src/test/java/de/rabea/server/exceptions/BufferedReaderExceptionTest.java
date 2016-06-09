package de.rabea.server.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class BufferedReaderExceptionTest {

    @Test
    public void takesAndReturnsMessage() {
        BufferedReaderException exception = new BufferedReaderException("message");
        assertEquals("message", exception.getMessage());
    }
}
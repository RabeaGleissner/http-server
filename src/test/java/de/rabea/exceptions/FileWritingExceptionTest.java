package de.rabea.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileWritingExceptionTest {

    @Test
    public void takesAndReturnsMessage() {
        FileWritingException exception = new FileWritingException("message");
        assertEquals("message", exception.getMessage());
    }
}
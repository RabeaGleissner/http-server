package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileWritingExceptionTest {

    @Test
    public void takesAndReturnsMessage() {
        FileWritingException exception = new FileWritingException("message");
        assertEquals("message", exception.getMessage());
    }
}
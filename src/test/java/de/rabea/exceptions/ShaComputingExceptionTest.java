package de.rabea.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShaComputingExceptionTest {

    @Test
    public void takesAndReturnsMessage() {
        ShaComputingException exception = new ShaComputingException("message");
        assertEquals("message", exception.getMessage());
    }
}
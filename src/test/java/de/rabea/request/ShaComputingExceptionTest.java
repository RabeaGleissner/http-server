package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShaComputingExceptionTest {

    @Test
    public void takesAndReturnsMessage() {
        ShaComputingException exception = new ShaComputingException("message");
        assertEquals("message", exception.getMessage());
    }
}
package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsTest {

    @Test
    public void returnsOptionsForMethodOptionsRoute() {
        Options response = new Options("/method_options");
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT", response.create());
    }

    @Test
    public void returnsOptionsForMethodOptions2Route() {
        Options response = new Options("/method_options2");
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,OPTIONS", response.create());
    }

    @Test
    public void returnsNullForUnknownRoute() {
        Options response = new Options("/unknown_options");
        assertEquals(null, response.create());
    }
}
package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsResponseGeneratorTest {

    @Test
    public void returnsOptionsForMethodOptionsRoute() {
        Options response = new Options("/method_options");
        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", response.create());
    }

    @Test
    public void returnsOptionsForMethodOptions2Route() {
        Options response = new Options("/method_options2");
        assertEquals("Allow: GET,OPTIONS", response.create());
    }

    @Test
    public void returnsNullForUnknownRoute() {
        Options response = new Options("/unknown_options");
        assertEquals(null, response.create());
    }
}
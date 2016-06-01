package de.rabea;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsResponseTest {

    @Test
    public void returnsOptionsForMethodOptionsRoute() {
        OptionsResponse response = new OptionsResponse("/method_options");
        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", response.generate());
    }

    @Test
    public void returnsOptionsForMethodOptions2Route() {
        OptionsResponse response = new OptionsResponse("/method_options2");
        assertEquals("Allow: GET,OPTIONS", response.generate());
    }

    @Test
    public void returnsNullForUnknownRoute() {
        OptionsResponse response = new OptionsResponse("/unknown_options");
        assertEquals(null, response.generate());
    }
}
package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectTest {

    @Test
    public void returns302ForRedirect() {
        Redirect redirect = new Redirect();
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/", redirect.create());
    }
}
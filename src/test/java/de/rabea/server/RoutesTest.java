package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoutesTest {

    private Routes routes;

    @Before
    public void setup() {
        routes = new Routes();
    }

    @Test
    public void returnsTrueIfRouteExists() {
        assertTrue(routes.isExisting("/form"));
    }

    @Test
    public void returnsFalseIfRouteDoesNotExist() {
        assertFalse(routes.isExisting("/foobar"));
    }

    @Test
    public void returnsTrueIfItIsRedirect() {
        assertTrue(routes.isRedirect("/redirect"));
    }
}
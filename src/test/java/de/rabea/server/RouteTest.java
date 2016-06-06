package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteTest {

    private Route route;
    private String directory;

    @Before
    public void setup() {
        route = new Route();
        directory = directory();
    }

    @Test
    public void returnsTrueIfRouteExists() {
        assertTrue(route.isExisting("/form", directory));
    }

    @Test
    public void returnsFalseIfRouteDoesNotExist() {
        assertFalse(route.isExisting("/foobar", directory));
    }

    @Test
    public void returnsTrueIfItIsRedirect() {
        assertTrue(route.isRedirect("/redirect"));
    }

    @Test
    public void isInPublicDirectory() {
        assertTrue(route.isInDirectory("file.txt", directory));
    }

    @Test
    public void publicDirectoryIsEmpty() {
        assertFalse(route.directoryHasContent("PUBLIC_DIR"));
    }

    @Test
    public void returnsOptionsForRequestedRoute() {
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", route.optionsFor("/method_options"));
    }
}
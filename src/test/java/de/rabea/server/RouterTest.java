package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouterTest {

    private Router router;
    private String directory;

    @Before
    public void setup() {
        router = new Router();
        directory = directory();
    }

    @Test
    public void returnsTrueIfRouteExists() {
        assertTrue(router.isExisting("/form", directory));
    }

    @Test
    public void returnsTrueIfItIsRedirect() {
        assertTrue(router.isRedirect("/redirect"));
    }

    @Test
    public void returnsTrueIfResourceIsInPublicDirectory() {
        assertTrue(router.isInDirectory("/file.txt", directory));
    }

    @Test
    public void returnsFalseIfPublicDirectoryIsEmpty() {
        assertFalse(router.directoryHasContent("PUBLIC_DIR"));
    }

    @Test
    public void returnsOptionsForRequestedRoute() {
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", router.optionsFor("/method_options"));
    }
}
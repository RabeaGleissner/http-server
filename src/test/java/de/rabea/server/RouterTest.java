package de.rabea.server;

import de.rabea.request.Directory;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static de.rabea.server.HttpVerb.POST;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class RouterTest {

    private Router router;
    private Directory directory;

    @Before
    public void setup() {
        directory = new Directory(directory());
        router = new Router(directory);
    }

    @Test
    public void returnsTrueIfRouteExists() {
        assertTrue(router.isExisting("/form"));
    }

    @Test
    public void returnsTrueIfItIsRedirect() {
        assertTrue(router.isRedirect("/redirect"));
    }

    @Test
    public void returnsOptionsForRequestedRoute() {
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", router.optionsFor("/method_options"));
    }

    @Test
    public void returnsFalseIfRequestMethodIsNotAllowed() {
        assertFalse(router.validMethod(POST, "/redirect"));
    }
}
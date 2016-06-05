package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResourceTest {

    private Resource resource;
    String directory;

    @Before
    public void setup() {
        resource = new Resource();
        directory = directory();
    }

    @Test
    public void returnsTrueIfRouteExists() {
        assertTrue(resource.isExisting("/form", directory));
    }

    @Test
    public void returnsFalseIfRouteDoesNotExist() {
        assertFalse(resource.isExisting("/foobar", directory));
    }

    @Test
    public void returnsTrueIfItIsRedirect() {
        assertTrue(resource.isRedirect("/redirect"));
    }

    @Test
    public void isInPublicDirectory() {
        assertTrue(resource.isInDirectory("file.txt", directory));
    }

    @Test
    public void publicDirectoryIsEmpty() {
        assertFalse(resource.directoryHasContent("PUBLIC_DIR"));
    }
}
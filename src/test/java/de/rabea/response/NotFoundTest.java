package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotFoundTest {

    @Test
    public void returns404ForRequestToNonExistentRoute() {
        NotFound notFound = new NotFound();
        assertEquals("HTTP/1.1 404 Not Found", notFound.create());
    }
}
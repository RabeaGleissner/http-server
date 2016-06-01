package de.rabea;

import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void returns200OKForGetRequest() {
        Response response = new Response(GET, "/");
        assertEquals("HTTP/1.0 200 OK", response.generate());
    }

    @Test
    public void returns404ForRequestToNonExistentRoute() {
        Response response = new Response(GET, "/foobar");
        assertEquals("HTTP/1.0 404 Not Found", response.generate());
    }
}
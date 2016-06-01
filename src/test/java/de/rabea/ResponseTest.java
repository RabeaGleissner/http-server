package de.rabea;

import org.junit.Test;

import static de.rabea.HttpVerb.*;
import static de.rabea.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class ResponseTest {
    private String protocol = "HTTP/1.1 ";

    @Test
    public void returns200OKForGetRequest() {
        Response response = new Response(GET, "/");
        assertEquals(protocol + "200 OK", response.generate());
    }

    @Test
    public void returns404ForRequestToNonExistentRoute() {
        Response response = new Response(GET, "/foobar");
        assertEquals(protocol + "404 Not Found", response.generate());
    }

    @Test
    public void returns202AndOptionsForMethodOptionsRoute() {
        Response response = new Response(OPTIONS, "/method_options");
        assertEquals(protocol + "200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT", response.generate());
    }

    @Test
    public void returns418AndTeapotForRequestToCoffeeRoute() {
        Response response = new Response(GET, "/coffee");
        assertEquals(protocol + "418 I'm a teapot\n\nI'm a teapot", response.generate());

    }
}
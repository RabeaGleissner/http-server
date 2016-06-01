package de.rabea.response;

import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static de.rabea.HttpVerb.OPTIONS;
import static org.junit.Assert.assertEquals;

public class ResponseTest {
    private String protocol = "HTTP/1.1 ";

    @Test
    public void returns200OKForGetRequest() {
        ResponseGenerator responseGenerator = new ResponseGenerator(GET, "/");
        assertEquals(protocol + "200 OK", responseGenerator.generate());
    }

    @Test
    public void returns404ForRequestToNonExistentRoute() {
        ResponseGenerator responseGenerator = new ResponseGenerator(GET, "/foobar");
        assertEquals(protocol + "404 Not Found", responseGenerator.generate());
    }

    @Test
    public void returns202AndOptionsForMethodOptionsRoute() {
        ResponseGenerator responseGenerator = new ResponseGenerator(OPTIONS, "/method_options");
        assertEquals(protocol + "200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT", responseGenerator.generate());
    }

    @Test
    public void returns418AndTeapotForRequestToCoffeeRoute() {
        ResponseGenerator responseGenerator = new ResponseGenerator(GET, "/coffee");
        assertEquals(protocol + "418 I'm a teapot\n\nI'm a teapot", responseGenerator.generate());
    }

    @Test
    public void returns302ForRedirect() {
        ResponseGenerator responseGenerator = new ResponseGenerator(GET, "/redirect");
        assertEquals(protocol + "302 Found\nLocation: http://localhost:5000/", responseGenerator.generate());
    }
}
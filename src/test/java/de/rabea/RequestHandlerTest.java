package de.rabea;

import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {

    @Test
    public void returnsHttpVerb() {
        RequestHandler requestHandler = new RequestHandler("GET / HTTP/1.1");
        assertEquals(GET, requestHandler.httpVerb());
    }

    @Test
    public void returnsRoute() {
        RequestHandler requestHandler = new RequestHandler("GET /form HTTP/1.1");
        assertEquals("/form", requestHandler.route());
    }
}
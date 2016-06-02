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

    @Test
    public void returnsBody() {
       RequestHandler requestHandler = new RequestHandler("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat");
        assertEquals("data=fatcat", requestHandler.body());
    }
}
package de.rabea.request;

import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.server.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class RequestTest {

    @Test
    public void returnsHttpVerb() {
        Request request = new Request("GET / HTTP/1.1");
        assertEquals(GET, request.httpVerb());
    }

    @Test
    public void returnsRoute() {
        Request request = new Request("GET /form HTTP/1.1");
        assertEquals("/form", request.route());
    }

    @Test
    public void returnsBodyForPostRequest() {
       Request request = new Request("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat");
        assertEquals("data=fatcat", asString(request.body()));
    }
}
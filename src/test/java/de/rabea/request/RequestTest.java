package de.rabea.request;

import org.junit.Test;

import static de.rabea.server.HttpVerb.GET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestTest {

    @Test
    public void returnsHttpVerb() {
        Request request = new Request("GET / HTTP/1.1");
        assertEquals(GET, request.httpVerb());
    }

    @Test
    public void returnsRouteForRoot() {
        Request request = new Request("GET / HTTP/1.1");
        assertEquals("/", request.route());
    }

    @Test
    public void returnsRouteForFormRoute() {
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
        assertEquals("data=fatcat", request.body());
    }

    @Test
    public void requestHasUrlParams() {
        Request request = new Request("GET /form?code=hello HTTP/1.1");
        assertTrue(request.hasUrlParams());
    }

    @Test
    public void returnsRangeForReadingFileContent() {
        Request request = new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4");
        assertEquals("0-4", request.range());
    }
}
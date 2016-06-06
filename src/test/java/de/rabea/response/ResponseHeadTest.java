package de.rabea.response;

import de.rabea.request.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseHeadTest {
    private final String protocol = "HTTP/1.1 ";

    @Test
    public void returns200OKForGetRequest() {
        Request request = new Request("GET / HTTP/1.1");
        ResponseHead responseHead = new ResponseHead(request, "PUBLIC_DIR");
        assertEquals(protocol + "200 OK\n\n", responseHead.generate());
    }

    @Test
    public void returns418AndTeapotForRequestToCoffeeRoute() {
        Request request = new Request("GET /coffee HTTP/1.1");
        ResponseHead responseHead = new ResponseHead(request, "PUBLIC_DIR");
        assertEquals("HTTP/1.1 418 I'm a teapot\n\nI'm a teapot\n\n", responseHead.generate());
    }

    @Test
    public void returns404ForRequestToNonExistentRoute() {
        Request request = new Request("GET /unknown HTTP/1.1");
        ResponseHead responseHead = new ResponseHead(request, "PUBLIC_DIR");
        assertEquals("HTTP/1.1 404 Not Found\n\n", responseHead.generate());
    }

    @Test
    public void returnsOptionsForMethodOptionsRoute() {
        Request request = new Request("OPTIONS /method_options HTTP/1.1");
        ResponseHead responseHead = new ResponseHead(request, "PUBLIC_DIR");
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT\n\n", responseHead.generate());
    }

    @Test
    public void returnsResponseHeaderForPartialContent() {
        Request request = new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4");
        ResponseHead responseHead = new ResponseHead(request, "PUBLIC_DIR");
        assertEquals("HTTP/1.1 206 Partial Content\n\n", responseHead.generate());
    }

    @Test
    public void returns302AndNewLocationForRedirect() {
        Request request = new Request("GET /redirect HTTP/1.1");
        ResponseHead responseHead = new ResponseHead(request, "PUBLIC_DIR");
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/\n\n", responseHead.generate());
    }
}
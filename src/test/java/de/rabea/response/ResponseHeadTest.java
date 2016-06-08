package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.Request;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ResponseHeadTest {
    private final String PROTOCOL = "HTTP/1.1 ";
    private final String EMPTY_LINE = "\n\n";
    private Directory directory;

    @Before
    public void setup() {
        directory = new Directory("PUBLIC_DIR");
    }

    @Test
    public void returns200ForGetRequest() {
        ResponseHead responseHead = new ResponseHead(new Request("GET / HTTP/1.1", directory));
        assertEquals(PROTOCOL + "200 OK" + EMPTY_LINE, responseHead.generate());
    }

    @Test
    public void returns418AndTeapotForRequestToCoffeeRoute() {
        ResponseHead responseHead = new ResponseHead(new Request("GET /coffee HTTP/1.1", directory));
        assertEquals(PROTOCOL + "418 I'm a teapot" + EMPTY_LINE + "I'm a teapot" + EMPTY_LINE, responseHead.generate());
    }

    @Test
    public void returns404ForRequestToNonExistentRoute() {
        ResponseHead responseHead = new ResponseHead(new Request("GET /unknown HTTP/1.1", directory));
        assertEquals(PROTOCOL + "404 Not Found" + EMPTY_LINE, responseHead.generate());
    }

    @Test
    public void returnsOptionsForMethodOptionsRoute() {
        ResponseHead responseHead = new ResponseHead(new Request("OPTIONS /method_options HTTP/1.1", directory));
        assertEquals(PROTOCOL + "200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT" + EMPTY_LINE, responseHead.generate());
    }

    @Test
    public void returns206ForPartialContent() {
        Directory directory = new Directory(directory());
        ResponseHead responseHead = new ResponseHead(new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4", directory));
        assertEquals(PROTOCOL + "206 Partial Content" + EMPTY_LINE, responseHead.generate());
    }

    @Test
    public void returns302AndNewLocationForRedirect() {
        ResponseHead responseHead = new ResponseHead(new Request("GET /redirect HTTP/1.1", directory));
        assertEquals(PROTOCOL + "302 Found\nLocation: http://localhost:5000/" + EMPTY_LINE, responseHead.generate());
    }
}
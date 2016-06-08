package de.rabea.request;

import org.junit.Before;
import org.junit.Test;

import static de.rabea.server.HttpVerb.GET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestTest {

    private Request simpleGetRequest;
    private Request requestWithParams;
    private Directory directory;

    @Before
    public void setup() {
        directory = new Directory("PUBLIC_DIR");
        simpleGetRequest = new Request("GET / HTTP/1.1", directory);
        requestWithParams = new Request("GET /form?code=hello HTTP/1.1", directory);
    }

    @Test
    public void returnsHttpVerb() {
        assertEquals(GET, simpleGetRequest.httpVerb);
    }

    @Test
    public void returnsRoute() {
        assertEquals("/", simpleGetRequest.route);
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
                "data=fatcat", directory);
        assertEquals("data=fatcat", request.body);
    }

    @Test
    public void returnsTrueIfRequestHasUrlParams() {
        assertTrue(requestWithParams.hasUrlParams());
    }

    @Test
    public void returnsUrlParams() {
        assertEquals("code = hello\n", requestWithParams.urlParams);
    }

    @Test
    public void returnsRangeForReadingFileContent() {
        Request request = new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4", directory);
        assertEquals("0-4", request.range);
    }
}
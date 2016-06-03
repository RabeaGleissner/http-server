package de.rabea;

import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class RequestSplitterTest {

    @Test
    public void returnsHttpVerb() {
        RequestSplitter requestSplitter = new RequestSplitter("GET / HTTP/1.1");
        assertEquals(GET, requestSplitter.httpVerb());
    }

    @Test
    public void returnsRoute() {
        RequestSplitter requestSplitter = new RequestSplitter("GET /form HTTP/1.1");
        assertEquals("/form", requestSplitter.route());
    }

    @Test
    public void returnsBody() {
       RequestSplitter requestSplitter = new RequestSplitter("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat");
        assertEquals("data=fatcat", requestSplitter.body());
    }
}
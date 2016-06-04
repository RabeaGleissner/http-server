package de.rabea.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputParserTest {
    private InputParser parser;

    @Before
    public void setup() {
         parser = new InputParser();
    }

    @Test
    public void knowsItIsGETRequest() {
        assertTrue(parser.isOneLiner("\n GET / HTTP/1.1\n"));
    }

    @Test
    public void findsContentLength() {
        assertEquals(11, parser.contentLength("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n"));
    }

    @Test
    public void returnsFalseIfRequestHasNoBody() {
        assertFalse(parser.hasBody("PUT /method_options HTTP/1.1\n" +
                "Content-Length: 0\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n"));
    }
}
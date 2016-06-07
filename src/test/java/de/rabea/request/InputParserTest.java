package de.rabea.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputParserTest {
    private InputParser parser;
    private String otherRequestDetails;

    @Before
    public void setup() {
        parser = new InputParser();
        otherRequestDetails = "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n";
    }

    @Test
    public void returnsContentLength() {
        assertEquals(11, parser.contentLength("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" + otherRequestDetails));
    }

    @Test
    public void returnsFalseIfRequestHasNoBody() {
        assertFalse(parser.hasBody("PUT /method_options HTTP/1.1\n" +
                "Content-Length: 0\n" + otherRequestDetails));
    }
}
package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseHeaderTest {
    private final String protocol = "HTTP/1.1 ";

    @Test
    public void returns200OKForGetRequest() {
        ResponseHeader responseHeader = new ResponseHeader(new TwoHundred());
        assertEquals(protocol + "200 OK\n\n", responseHeader.generate());
    }
}
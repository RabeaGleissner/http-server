package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TwoHundredTest {

    @Test
    public void returns200OKForGetRequest() {
        TwoHundred twoHundred = new TwoHundred("");
        assertEquals("HTTP/1.1 200 OK", twoHundred.create());
    }

    @Test
    public void returnsStatusAndBodyIfAvailable() {
        TwoHundred twoHundred = new TwoHundred("data=fatcat");
        assertEquals("HTTP/1.1 200 OK\n\ndata=fatcat", twoHundred.create());
    }
}
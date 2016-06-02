package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandardGetTest {

    @Test
    public void returns200OKForGetRequest() {
        TwoHundred twoHundred = new TwoHundred();
        assertEquals("HTTP/1.1 200 OK", twoHundred.create());
    }
}
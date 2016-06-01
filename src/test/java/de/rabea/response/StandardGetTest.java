package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.*;

public class StandardGetTest {

    @Test
    public void returns200OKForGetRequest() {
        StandardGet standardGet = new StandardGet();
        assertEquals("HTTP/1.1 200 OK", standardGet.create());
    }
}
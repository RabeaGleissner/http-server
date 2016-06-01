package de.rabea;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void returns200OKForGetRequest() {
        String requestMessage =  "GET / HTTP/1.1";
        Response response = new Response(requestMessage);
        assertEquals("HTTP/1.0 200 OK", response.generate());
    }
}
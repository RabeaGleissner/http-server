package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseGeneratorTest {
    private final String protocol = "HTTP/1.1 ";

    @Test
    public void returns200OKForGetRequest() {
        ResponseGenerator responseGenerator = new ResponseGenerator(new TwoHundred());
        assertEquals(protocol + "200 OK\n\n", responseGenerator.generate());
    }
}
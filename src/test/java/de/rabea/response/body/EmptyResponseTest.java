package de.rabea.response.body;

import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static org.junit.Assert.assertEquals;

public class EmptyResponseTest {
    @Test
    public void returnsEmptyStringAsResponse() {
        EmptyResponse emptyResponse = new EmptyResponse();
        assertEquals("", asString(emptyResponse.response()));
    }
}
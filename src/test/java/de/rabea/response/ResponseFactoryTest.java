package de.rabea.response;

import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static de.rabea.HttpVerb.OPTIONS;
import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {
    private final String requestBody = "";


    @Test
    public void returnsRedirectResponseForRedirect() {
        ResponseFactory factory = new ResponseFactory(GET, "/redirect", requestBody);
        assertTrue(factory.create() instanceof Redirect);
    }

    @Test
    public void returnsTeapotResponse() {
        ResponseFactory factory= new ResponseFactory(GET, "/coffee", requestBody);
        assertTrue(factory.create() instanceof FourEighteen);
    }

    @Test
    public void returnsOptionsResponseIfVerbIsOptions() {
        ResponseFactory factory = new ResponseFactory(OPTIONS, "/", requestBody);
        assertTrue(factory.create() instanceof Options);
    }

    @Test
    public void returnsStandardOKResponseForExistingRoute() {
        ResponseFactory factory = new ResponseFactory(GET, "/", requestBody);
        assertTrue(factory.create() instanceof TwoHundred);
    }

    @Test
    public void returnsNotFoundResponseForNonExistantRoute() {
        ResponseFactory factory = new ResponseFactory(GET, "/foobar", requestBody);
        assertTrue(factory.create() instanceof NotFound);
    }
}
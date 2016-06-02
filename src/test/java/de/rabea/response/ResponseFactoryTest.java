package de.rabea.response;

import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static de.rabea.HttpVerb.OPTIONS;
import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {


    @Test
    public void returnsRedirectResponseForRedirect() {
        ResponseFactory factory = new ResponseFactory(GET, "/redirect");
        assertTrue(factory.create() instanceof Redirect);
    }

    @Test
    public void returnsTeapotResponse() {
        ResponseFactory factory= new ResponseFactory(GET, "/coffee");
        assertTrue(factory.create() instanceof FourEighteen);
    }

    @Test
    public void returnsOptionsResponseIfVerbIsOptions() {
        ResponseFactory factory = new ResponseFactory(OPTIONS, "/");
        assertTrue(factory.create() instanceof Options);
    }

    @Test
    public void returnsStandardOKResponseForExistingRoute() {
        ResponseFactory factory = new ResponseFactory(GET, "/");
        assertTrue(factory.create() instanceof TwoHundred);
    }

    @Test
    public void returnsNotFoundResponseForNonExistantRoute() {
        ResponseFactory factory = new ResponseFactory(GET, "/foobar");
        assertTrue(factory.create() instanceof NotFound);
    }
}
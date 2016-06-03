package de.rabea.response;

import de.rabea.request.Directory;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.server.HttpVerb.GET;
import static de.rabea.server.HttpVerb.OPTIONS;
import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {
    private final String requestBody = "";

    String directory;

    @Before
    public void setup() {
        this.directory = new Directory().forResource();
    }

    @Test
    public void returnsRedirectResponseForRedirect() {
        ResponseFactory factory = new ResponseFactory(GET, "/redirect", requestBody, directory);
        assertTrue(factory.create() instanceof Redirect);
    }

    @Test
    public void returnsTeapotResponse() {
        ResponseFactory factory= new ResponseFactory(GET, "/coffee", requestBody, directory);
        assertTrue(factory.create() instanceof FourEighteen);
    }

    @Test
    public void returnsOptionsResponseIfVerbIsOptions() {
        ResponseFactory factory = new ResponseFactory(OPTIONS, "/", requestBody, directory);
        assertTrue(factory.create() instanceof Options);
    }

    @Test
    public void returnsStandardOKResponseForExistingRoute() {
        ResponseFactory factory = new ResponseFactory(GET, "/", requestBody, directory);
        assertTrue(factory.create() instanceof TwoHundred);
    }

    @Test
    public void returnsNotFoundResponseForNonExistantRoute() {
        ResponseFactory factory = new ResponseFactory(GET, "/foobar", requestBody, directory);
        assertTrue(factory.create() instanceof NotFound);
    }
}
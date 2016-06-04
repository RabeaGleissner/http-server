package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.RequestStub;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.server.HttpVerb.GET;
import static de.rabea.server.HttpVerb.OPTIONS;
import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {

    private String directory;
    private RequestStub getRequest;

    @Before
    public void setup() {
        this.directory = new Directory().forResource();
        this.getRequest = new RequestStub(GET);
    }

    @Test
    public void returnsRedirectResponseForRedirect() {
        ResponseFactory factory = new ResponseFactory(getRequest, "/redirect", directory);
        assertTrue(factory.create() instanceof Redirect);
    }

    @Test
    public void returnsTeapotResponse() {
        ResponseFactory factory= new ResponseFactory(getRequest, "/coffee", directory);
        assertTrue(factory.create() instanceof FourEighteen);
    }

    @Test
    public void returnsOptionsResponseIfVerbIsOptions() {
        ResponseFactory factory = new ResponseFactory(new RequestStub(OPTIONS), "/", directory);
        assertTrue(factory.create() instanceof Options);
    }

    @Test
    public void returnsStandardOKResponseForExistingRoute() {
        ResponseFactory factory = new ResponseFactory(getRequest, "/", directory);
        assertTrue(factory.create() instanceof TwoHundred);
    }

    @Test
    public void returnsNotFoundResponseForNonExistentRoute() {
        ResponseFactory factory = new ResponseFactory(getRequest, "/foobar", directory);
        assertTrue(factory.create() instanceof NotFound);
    }

    @Test
    public void returnsPartialContentResponse() {
        boolean partial = true;
        ResponseFactory factory = new ResponseFactory(new RequestStub(GET, partial), "/", directory);
        assertTrue(factory.create() instanceof PartialContent);
    }
}
package de.rabea.response;

import de.rabea.request.RequestStub;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.*;
import static de.rabea.server.HttpVerb.GET;
import static de.rabea.server.HttpVerb.OPTIONS;
import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {

    private String directory;

    @Before
    public void setup() {
        this.directory = directory();
    }

    @Test
    public void returnsRedirectResponseForRedirect() {
        RequestStub requestStub = new RequestStub(GET, "/redirect");
        ResponseFactory factory = new ResponseFactory(requestStub, directory);
        assertTrue(factory.create() instanceof Redirect);
    }

    @Test
    public void returnsTeapotResponse() {
        RequestStub requestStub = new RequestStub(GET, "/coffee");
        ResponseFactory factory = new ResponseFactory(requestStub, directory);
        assertTrue(factory.create() instanceof FourEighteen);
    }

    @Test
    public void returnsOptionsResponseIfVerbIsOptions() {
        ResponseFactory factory = new ResponseFactory(new RequestStub(OPTIONS, "/"), directory);
        assertTrue(factory.create() instanceof Options);
    }

    @Test
    public void returnsStandardOKResponseForExistingRoute() {
        ResponseFactory factory = new ResponseFactory(new RequestStub(GET, "/"), directory);
        assertTrue(factory.create() instanceof TwoHundred);
    }

    @Test
    public void returnsNotFoundResponseForNonExistentRoute() {
        ResponseFactory factory = new ResponseFactory(new RequestStub(GET, "/foobar"), directory);
        assertTrue(factory.create() instanceof NotFound);
    }

    @Test
    public void returnsPartialContentResponse() {
        boolean partial = true;
        ResponseFactory factory = new ResponseFactory(new RequestStub(GET, "/file.txt", partial), directory);
        assertTrue(factory.create() instanceof PartialContent);
    }
}
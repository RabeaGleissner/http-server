package de.rabea;

import de.rabea.server.ContentStorage;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class RequestTest {

    private ContentStorage contentStorage;

    @Before
    public void setup() {
       this.contentStorage = new ContentStorage();
    }

    @Test
    public void returnsHttpVerb() {
        Request request = new Request("GET / HTTP/1.1", contentStorage);
        assertEquals(GET, request.httpVerb());
    }

    @Test
    public void returnsRoute() {
        Request request = new Request("GET /form HTTP/1.1", contentStorage);
        assertEquals("/form", request.route());
    }

    @Test
    public void returnsBodyForPostRequest() {
       Request request = new Request("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat", contentStorage);
        assertEquals("data=fatcat", request.body());
    }

    @Test
    public void savesURLParametersAsBody() {
        String params = "?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        Request request = new Request("GET /parameters" + params + " HTTP/1.1", contentStorage);
        assertEquals("/parameters", request.route());
        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff", request.urlParams());
    }

    @Test
    public void returnsBodyForUrlParameters() {
        String params = "?variable_1=Operators%20all%22%3F&variable_2=stuff";
        Request request = new Request("GET /parameters" + params + " HTTP/1.1", contentStorage);
        assertEquals("variable_1 = Operators all\"?\nvariable_2 = stuff", request.body());
    }

    @Test
    public void savesRequestParamsInContentStorage() {
        new Request("GET /form?code=123&var=hey HTTP/1.1", contentStorage);
        assertEquals("code = 123\nvar = hey", contentStorage.getContentFor("/form"));
    }
}
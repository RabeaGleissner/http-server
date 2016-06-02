package de.rabea;

import org.junit.Test;

import static de.rabea.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class RequestSplitterTest {

    @Test
    public void returnsHttpVerb() {
        RequestSplitter requestSplitter = new RequestSplitter("GET / HTTP/1.1");
        assertEquals(GET, requestSplitter.httpVerb());
    }

    @Test
    public void returnsRoute() {
        RequestSplitter requestSplitter = new RequestSplitter("GET /form HTTP/1.1");
        assertEquals("/form", requestSplitter.route());
    }

    @Test
    public void returnsBody() {
       RequestSplitter requestSplitter = new RequestSplitter("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat");
        assertEquals("data=fatcat", requestSplitter.body());
    }

    @Test
    public void savesURLParametersAsBody() {
        String params = "?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        RequestSplitter requestSplitter = new RequestSplitter("GET /parameters" + params + " HTTP/1.1" );
        assertEquals("/parameters", requestSplitter.route());
//        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?", requestSplitter.urlParams());
    }
}
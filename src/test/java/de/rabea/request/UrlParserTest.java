package de.rabea.request;

import de.rabea.TestHelper;
import de.rabea.server.ContentStorage;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.*;
import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class UrlParserTest {

    private ContentStorage contentStorage;
    private String directory;

    @Before
    public void setup() {
        this.contentStorage = new ContentStorage();
        this.directory = directory();
    }

    @Test
    public void savesURLParametersAsBody() {
        String params = "?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        Request request = new Request("GET /parameters" + params + " HTTP/1.1", contentStorage, directory);
        assertEquals("/parameters", request.route());
        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff", request.urlParams());
    }

    @Test
    public void returnsBodyForUrlParameters() {
        String params = "?variable_1=Operators%20all%22%3F&variable_2=stuff";
        Request request = new Request("GET /parameters" + params + " HTTP/1.1", contentStorage, directory);
        assertEquals("variable_1 = Operators all\"?\nvariable_2 = stuff", asString(request.body()));
    }
}
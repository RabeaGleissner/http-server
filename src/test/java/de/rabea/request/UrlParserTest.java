package de.rabea.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UrlParserTest {

    private String params;

    @Before
    public void setup() {
        this.params = "?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20" +
                "-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20" +
                "that%20all%22%3F&variable_2=stuff";
    }

    @Test
    public void returnsOneUrlParameter() {
        UrlParser parser = new UrlParser("/parameter?say=hello");
        String parameter = "say = hello\n";
        assertEquals(parameter, parser.parameters());
    }

    @Test
    public void returnsTwoDecodedUrlParameters() {
        UrlParser parser = new UrlParser("/parameters" + params);
        String decodedParameters = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff";
        assertEquals(decodedParameters, parser.parameters());
    }

    @Test
    public void returnsTrueIfUrlParamsExist() {
        UrlParser parser = new UrlParser("/parameters" + params);
        assertTrue(parser.hasParams());
    }

    @Test
    public void returnsEmptyStringIfUrlHasNoParams() {
        UrlParser parser = new UrlParser("/no-params");
        assertEquals("", parser.parameters());
    }
}
package de.rabea.response;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeapotTest {

    @Test
    public void returns418AndTeapotForRequestToCoffeeRoute() {
        Teapot teapot = new Teapot();
        assertEquals("HTTP/1.1 418 I'm a teapot\n\nI'm a teapot", teapot.create());
    }
}
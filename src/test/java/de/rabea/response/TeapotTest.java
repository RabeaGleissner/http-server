package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeapotTest {

    @Test
    public void returns418AndTeapotForRequestToCoffeeRoute() {
        FourEighteen fourEighteen = new FourEighteen();
        assertEquals("HTTP/1.1 418 I'm a teapot\n\nI'm a teapot", fourEighteen.create());
    }
}
package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogTest {
    @Test
    public void savesRequestHead() {
        Log log = new Log();
        log.register("request one");
        log.register("request two");
        assertEquals("request one\nrequest two\n", log.show());
    }
}
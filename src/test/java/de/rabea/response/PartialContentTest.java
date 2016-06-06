package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.*;

public class PartialContentTest {
    @Test
    public void returnsResponseHeaderForPartialContent() {
        PartialContent partialContent = new PartialContent();
        assertEquals("HTTP/1.1 206 Partial Content\n\n", partialContent.create());
    }
}
package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentHolderTest {
    private final String formUrl = "/form";
    private ContentHolder holder;

    @Before
    public void setup() {
        this.holder = new ContentHolder();
    }

    @Test
    public void savesAndReturnsContent() {
        holder.save(formUrl, "some body content");
        assertEquals("some body content", holder.getContentFor(formUrl));
    }

    @Test
    public void deletesContent() {
        holder.save(formUrl, "some body content");
        holder.deleteFor(formUrl);
        assertEquals("", holder.getContentFor(formUrl));
    }
}
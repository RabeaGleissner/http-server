package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentStorageTest {
    private final String formUrl = "/form";
    private ContentStorage holder;

    @Before
    public void setup() {
        this.holder = new ContentStorage();
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
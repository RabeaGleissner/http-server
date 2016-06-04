package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
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
        holder.save(formUrl, "some body content".getBytes());
        assertEquals("some body content", asString(holder.getContentFor(formUrl)));
    }

    @Test
    public void deletesContent() {
        holder.save(formUrl, "some body content".getBytes());
        holder.deleteFor(formUrl);
        assertEquals("", asString(holder.getContentFor(formUrl)));
    }
}
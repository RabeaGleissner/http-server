package de.rabea.server;

import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.server.HttpVerb.*;
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
        assertEquals("some body content", asString(holder.bodyFor(formUrl)));
    }

    @Test
    public void deletesContentIfHttpVerbIsDelete() {
        holder.save(formUrl, "some body content".getBytes());
        holder.update(formUrl, new byte[0], DELETE);
        assertEquals("", asString(holder.bodyFor(formUrl)));
    }
}
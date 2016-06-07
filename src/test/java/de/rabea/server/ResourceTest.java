package de.rabea.server;

import org.junit.Test;

import static de.rabea.server.Resource.FORM;
import static org.junit.Assert.*;

public class ResourceTest {

    @Test
    public void returnsActualUrlForGivenKeyword() {
        assertEquals("/form", FORM.url());
    }
}
package de.rabea.response.body;

import de.rabea.request.Directory;
import de.rabea.request.Request;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ParamsTest {
    @Test
    public void returnsUrlParams() {
        Params params = new Params(new Request("GET /form?code=hello HTTP/1.1", new Directory(directory())));
        assertEquals("code = hello\n", asString(params.response()));
    }

    @Test
    public void returnsEmptyStringWhenThereAreNoParams() {
        Params params = new Params(new Request("GET /form HTTP/1.1", new Directory(directory())));
        assertEquals("", asString(params.response()));
    }
}
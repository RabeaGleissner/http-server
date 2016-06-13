package de.rabea.response.body;

import de.rabea.request.Directory;
import de.rabea.request.Request;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class PostResponseTest {

    @Test
    public void returnsPostRequestBodyData() {
        Request request = new Request("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "\n" +
                "data=fatcat", new Directory(directory()));
        PostResponse response = new PostResponse(request);
        assertEquals("data=fatcat", asString(response.response()));
    }
}
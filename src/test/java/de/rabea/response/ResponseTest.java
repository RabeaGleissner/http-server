package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.Request;
import de.rabea.server.ContentStorage;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void returnsResponseBody() {
        Response response = new Response(new RequestDummy(),
                new ContentStorageStub());
        assertEquals("some content", asString(response.body()));
    }

    @Test
    public void returnsResponseHeader() {
        Response response = new Response(new Request("GET / HTTP/1.1", new Directory("PUBLIC_DIR")),
                new ContentStorage());
        assertEquals("HTTP/1.1 200 OK\n\n", response.head());
    }

    private class ContentStorageStub extends ContentStorage {

        public byte[] bodyFor(String route) {
          return "some content".getBytes();
        }
    }

    private class RequestDummy extends Request {
    }
}
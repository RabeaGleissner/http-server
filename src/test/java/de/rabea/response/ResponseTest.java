package de.rabea.response;

import de.rabea.request.Request;
import de.rabea.server.ContentStorage;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void returnsResponseBody() {
        RequestDummy requestDummy = new RequestDummy();
        ContentStorageStub contentStorage = new ContentStorageStub();

        Response response = new Response(requestDummy, directory(), contentStorage);
        assertEquals("some content", asString(response.body()));
    }

    @Test
    public void returnsResponseHeader() {
        Request request = new Request("GET / HTTP/1.1");
        Response response = new Response(request, "PUBLIC_DIR", new ContentStorage());
        assertEquals("HTTP/1.1 200 OK\n\n", response.head());
    }

    private class ContentStorageStub extends ContentStorage {

        public byte[] bodyFor(String route) {
          return "some content".getBytes();
        }
    }

    private class RequestDummy extends Request {
        public String route() {
           return "";
        }
    }

}

package de.rabea.request;

import de.rabea.Helper;
import de.rabea.server.ContentStorage;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.Helper.*;
import static de.rabea.server.HttpVerb.GET;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestTest {

    private ContentStorage contentStorage;
    String currentDirectory;

    @Before
    public void setup() {
        this.contentStorage = new ContentStorage();
        this.currentDirectory = new Directory().forResource();
    }

    @Test
    public void returnsHttpVerb() {
        Request request = new Request("GET / HTTP/1.1", contentStorage, currentDirectory);
        assertEquals(GET, request.httpVerb());
    }

    @Test
    public void returnsRoute() {
        Request request = new Request("GET /form HTTP/1.1", contentStorage, currentDirectory);
        assertEquals("/form", request.route());
    }

    @Test
    public void returnsBodyForPostRequest() {
       Request request = new Request("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat", contentStorage, currentDirectory);
        assertEquals("data=fatcat", asString(request.body()));
    }

    @Test
    public void savesRequestParamsInContentStorage() {
        new Request("GET /form?code=123&var=hey HTTP/1.1", contentStorage, currentDirectory);
        assertEquals("code = 123\nvar = hey", asString(contentStorage.getContentFor("/form")));
    }

    @Test
    public void deletesStoredContent() {
        new Request("GET /form?code=123&var=hey HTTP/1.1", contentStorage, currentDirectory);
        new Request("DELETE /form HTTP/1.1", contentStorage, currentDirectory);
        assertEquals("", asString(contentStorage.getContentFor("/form")));
    }

    @Test
    public void savesPartialFileContentInContentStorage() {
        Request request = new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4", contentStorage, currentDirectory);
        int[] range = {0,4};
        String partialContent = "Some ";
        assertEquals(partialContent, asString(contentStorage.getContentFor("/file.txt")));
    }

//    @Test
//    public void savesPartialContentWhenOnlyRangeEndIsGiven() {
//        Request request = new Request("GET /file.txt HTTP/1.1\nRange: bytes=-6", contentStorage, currentDirectory);
//        // 6 from end
//        assertEquals("Some", contentStorage.getContentFor("/file.txt"));
//    }
//
//    @Test
//    public void savesPartialContentWithOnlyRangeStartGiven() {
//        Request request = new Request("GET /file.txt HTTP/1.1\nRange: bytes=4-", contentStorage, currentDirectory);
//        // 4 to end
//        assertEquals("Some", contentStorage.getContentFor("/file.txt"));
//    }
}
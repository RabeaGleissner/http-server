package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.Request;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ResponseBodyTest {

    private Request requestWithBody;
    private Request requestWithParams;
    private Request requestToFile;
    private Request getRequest;
    private Directory directory;

    @Before
    public void setup() {
        directory = new Directory(directory());
        requestWithBody= new Request("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat", directory);
        requestWithParams = new Request("GET /form?code=hello HTTP/1.1", directory);
        requestToFile = new Request("GET /file.txt HTTP/1.1\nRange: bytes=0-4", directory);
        getRequest = new Request("GET / HTTP/1.1", directory);
    }

    @Test
    public void returnsRequestBody() {
        ResponseBody body = new ResponseBody(requestWithBody);
        assertEquals("data=fatcat", body.receivedMessage());
    }

    @Test
    public void returnsParams() {
        ResponseBody body = new ResponseBody(requestWithParams);
        assertEquals("code = hello\n", body.receivedMessage());
    }

    @Test
    public void returnsEmptyStringIfItHasNotReceivedMessages() {
        ResponseBody body = new ResponseBody(getRequest);
        assertEquals("", body.receivedMessage());
    }

    @Test
    public void returnsFileContentIfRequestToFileIsMade() {
        ResponseBody body = new ResponseBody(requestToFile);
        assertEquals("Some ", asString(body.create()));
    }

    @Test
    public void returnsLinksToFilesInDirectory() {
        ResponseBody body = new ResponseBody(getRequest);
        assertEquals("<a href=/file.txt>file.txt</a>", asString(body.create()));
    }
}
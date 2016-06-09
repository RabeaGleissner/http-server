package de.rabea.server;

import de.rabea.request.Directory;
import de.rabea.request.Log;
import de.rabea.request.Request;
import org.junit.Test;

import static de.rabea.TestHelper.directory;
import static org.junit.Assert.assertEquals;

public class ControllerTest {

    @Test
    public void returnsLogsIfRequired() {
        Controller controller = new Controller(new Request("GET /logs HTTP/1.1", new Directory(directory())), new Log());
        assertEquals("GET /logs HTTP/1.1\n", controller.response());
    }
}
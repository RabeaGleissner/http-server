package de.rabea.response.body;

import de.rabea.request.Log;
import org.junit.Test;

import static de.rabea.TestHelper.asString;
import static org.junit.Assert.assertEquals;

public class LogsTest {
    @Test
    public void returnsLogs() {
        Logs logs = new Logs(new LogStub());
        assertEquals("test logs", asString(logs.response()));
    }

    private class LogStub extends Log {
        public String show() {
           return "test logs";
        }
    }
}
package de.rabea.server;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {

    @Test
    public void returnsPortAndDirectoryWhenGiven() {
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "1234");
        parsedArguments.put("directory", "DIR");

        Arguments arguments = new Arguments(new String[]{"-p", "1234", "-d", "DIR"});

        assertEquals(parsedArguments, arguments.parse());
    }

    @Test
    public void returnsDirectory() {
        Arguments arguments = new Arguments(new String[]{"-p", "1234", "-d", "DIR"});
        assertEquals("DIR", arguments.directory());
    }

    @Test
    public void returnsPortAndDefaultDirectoryWhenOnlyPortIsGiven() {
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "1234");
        parsedArguments.put("directory", "PUBLIC_DIR");

        Arguments arguments = new Arguments(new String[]{"-p", "1234"});

        assertEquals(parsedArguments, arguments.parse());
    }

    @Test
    public void returnsDirectoryAndDefaultPortWhenOnlyDirectoryIsGiven() {
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "5000");
        parsedArguments.put("directory", "DIR");

        Arguments arguments = new Arguments(new String[]{"-d", "DIR"});

        assertEquals(parsedArguments, arguments.parse());
    }

    @Test
    public void returnsDefaultWhenNoArgumentsAreGiven() {
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "5000");
        parsedArguments.put("directory", "PUBLIC_DIR");

        Arguments arguments = new Arguments(new String[]{});

        assertEquals(parsedArguments, arguments.parse());
    }
}
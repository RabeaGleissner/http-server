package de.rabea.server;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {

    @Test
    public void returnsPortAndDirectoryWhenGiven() {
        String[] commandLineArgs = {"-p", "1234", "-d", "DIR"};
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "1234");
        parsedArguments.put("directory", "DIR");

        Arguments arguments = new Arguments(commandLineArgs);

        assertEquals(parsedArguments, arguments.parse());
    }

    @Test
    public void returnsDirectory() {
        String[] commandLineArgs = {"-p", "1234", "-d", "DIR"};
        Arguments arguments = new Arguments(commandLineArgs);

        assertEquals("DIR", arguments.directory());
    }

    @Test
    public void returnsPortAndDefaultDirectoryWhenOnlyPortIsGiven() {
        String[] commandLineArgs = {"-p", "1234"};
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "1234");
        parsedArguments.put("directory", "PUBLIC_DIR");

        Arguments arguments = new Arguments(commandLineArgs);

        assertEquals(parsedArguments, arguments.parse());
    }

    @Test
    public void returnsDirectoryAndDefaultPortWhenOnlyDirectoryIsGiven() {
        String[] commandLineArgs = {"-d", "DIR"};
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "5000");
        parsedArguments.put("directory", "DIR");

        Arguments arguments = new Arguments(commandLineArgs);

        assertEquals(parsedArguments, arguments.parse());
    }

    @Test
    public void returnsDefaultWhenNoArgumentsAreGiven() {
        String[] noCommandLineArgs = {};
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put("port", "5000");
        parsedArguments.put("directory", "PUBLIC_DIR");

        Arguments arguments = new Arguments(noCommandLineArgs);

        assertEquals(parsedArguments, arguments.parse());
    }
}
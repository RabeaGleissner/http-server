package de.rabea.server;

import java.util.HashMap;
import java.util.Map;

public class Arguments {

    private final String[] arguments;

    public Arguments(String[] arguments) {
        this.arguments = arguments;
    }

    public String directory() {
       return parse().get("directory");
    }

    public Map<String, String> parse() {
        Map<String, String> parsedArguments = new HashMap<>();
        if (arguments.length == 0) {
           parsedArguments = defaultArguments();
        }

        if (arguments.length == 2) {
            Map<String, String> firstArguments = addFirst();
            parsedArguments = addDefaultSecond(firstArguments);
        }

        if (arguments.length == 4) {
            Map<String, String> firstArguments = addFirst();
            parsedArguments = addSecond(firstArguments);
        }
        parsedArguments = formatDirectory(parsedArguments);
        return parsedArguments;
    }

    private Map<String, String> formatDirectory(Map<String, String> parsedArguments) {
        String directory = parsedArguments.get("directory");
        if (!directory.equals("PUBLIC_DIR")) {
           directory = trailingSlash(directory);
            parsedArguments.put("directory", directory);
        }
        return parsedArguments;
    }

    private String trailingSlash(String directory) {
        if ((directory.substring(directory.length() - 1).equals("/"))) {
            return directory;
        } else {
            return directory + "/";
        }
    }

    private Map<String, String> defaultArguments() {
        Map<String, String> parsedArguments = new HashMap<>();
        parsedArguments = addDefaultPort(parsedArguments);
        parsedArguments = addDefaultDirectory(parsedArguments);
        return parsedArguments;
    }

    private Map<String, String> addDefaultSecond(Map<String, String> firstArguments) {
        if (firstArguments.containsKey("port")) {
            firstArguments = addDefaultDirectory(firstArguments);
        } else if (firstArguments.containsKey("directory")) {
            firstArguments = addDefaultPort(firstArguments);
        }
        return firstArguments;
    }

    private Map<String, String> addFirst() {
        Map<String, String> parsedArguments = new HashMap<>();
        parsedArguments.put(fullWord(arguments[0]), arguments[1]);
        return parsedArguments;
    }

    private Map<String, String> addSecond(Map<String, String> parsedArguments) {
        parsedArguments.put(fullWord(arguments[2]), arguments[3]);
        return parsedArguments;
    }

    private String fullWord(String argument) {
        if (argument.equals("-p")) {
            return "port";
        } else {
            return "directory";
        }
    }

    private Map<String, String> addDefaultPort(Map<String, String> parsedArguments) {
        parsedArguments.put("port", "5000");
        return parsedArguments;
    }

    private Map<String, String> addDefaultDirectory(Map<String, String> parsedArguments) {
        parsedArguments.put("directory", "PUBLIC_DIR");
        return parsedArguments;
    }
}

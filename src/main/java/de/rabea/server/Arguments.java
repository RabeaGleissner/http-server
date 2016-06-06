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
        return parsedArguments;
    }

    private Map<String, String> defaultArguments() {
        HashMap<String, String> parsedArguments = new HashMap<>();
        parsedArguments = addDefaultPort(parsedArguments);
        parsedArguments = addDefaultDirectory(parsedArguments);
        return parsedArguments;
    }

    private Map<String, String> addDefaultSecond(Map<String, String> firstArguments) {
        if (firstArguments.containsKey("port")) {
            firstArguments.put("directory", "PUBLIC_DIR");
        } else if (firstArguments.containsKey("directory")) {
            firstArguments.put("port", "5000");
        }
        return firstArguments;
    }

    private Map<String, String> addFirst() {
        HashMap<String, String> parsedArguments = new HashMap<>();
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

    private HashMap<String, String> addDefaultPort(HashMap<String, String> parsedArguments) {
        parsedArguments.put("port", "5000");
        return parsedArguments;
    }

    private HashMap<String, String> addDefaultDirectory(HashMap<String, String> parsedArguments) {
        parsedArguments.put("directory", "PUBLIC_DIR");
        return parsedArguments;
    }
}

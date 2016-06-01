package de.rabea;

import java.util.HashMap;

public class Arguments {

    private final String[] arguments;

    public Arguments(String[] arguments) {
        this.arguments = arguments;
    }

    public HashMap<String, String> parse() {
        HashMap<String, String> parsedArguments = new HashMap<String, String>();
        if (arguments.length == 0) {
            defaultArguments(parsedArguments);
        }

        if (arguments.length == 2) {
            addFirst(parsedArguments);
            addDefaultSecond(parsedArguments);
        }

        if (arguments.length == 4) {
            addFirst(parsedArguments);
            addSecond(parsedArguments);
        }
        return parsedArguments;
    }

    private void defaultArguments(HashMap<String, String> parsedArguments) {
        parsedArguments.put("port", "5000");
        parsedArguments.put("directory", "PUBLIC_DIR");
    }

    private void addDefaultSecond(HashMap<String, String> parsedArguments) {
        if (parsedArguments.containsKey("port")) {
            parsedArguments.put("directory", "PUBLIC_DIR");
        } else if (parsedArguments.containsKey("directory")) {
            parsedArguments.put("port", "5000");
        }
    }

    private void addFirst(HashMap<String, String> parsedArguments) {
        parsedArguments.put(fullWord(arguments[0]), arguments[1]);
    }

    private void addSecond(HashMap<String, String> parsedArguments) {
        parsedArguments.put(fullWord(arguments[2]), arguments[3]);
    }

    private String fullWord(String argument) {
        if (argument.equals("-p")) {
            return "port";
        } else {
            return "directory";
        }
    }
}

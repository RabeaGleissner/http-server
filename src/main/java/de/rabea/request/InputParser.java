package de.rabea.request;

import static de.rabea.server.HttpVerb.*;

public class InputParser {

//    public boolean isOneLiner(String line) {
//        return requestWithoutBody(strip(line).split(" ")[0]);
//    }

    private String strip(String line) {
        String noLineBreaks = line.replaceAll(System.getProperty("line.separator"), "");
        return noLineBreaks.trim();
    }

    public int contentLength(String request) {
        String[] lines = request.trim().split("\n");
        for (String line : lines) {
            Integer length = returnLength(line);
            if (length != null) return length;
        }
        return 0;
    }

    private Integer returnLength(String line) {
        String[] words = line.split(" ");
        if (words[0].equals("Content-Length:")) {
            return Integer.parseInt(words[1]);
        }
        return null;
    }

    public boolean hasBody(String request) {
        return contentLength(request) > 0;
    }

    private boolean requestWithoutBody(String requestedVerb) {
       return requestedVerb.equals(GET.toString()) ||
               requestedVerb.equals(HEAD.toString()) ||
               requestedVerb.equals(OPTIONS.toString());
    }
}

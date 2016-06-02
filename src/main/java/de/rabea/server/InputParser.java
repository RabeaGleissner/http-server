package de.rabea.server;

import static de.rabea.HttpVerb.*;

public class InputParser {

    public boolean isOneLiner(String line) {
        return requestWithoutBody(strip(line).split(" ")[0]);
    }

    public String strip(String line) {
        String noLineBreaks = line.replaceAll(System.getProperty("line.separator"), "");
        return noLineBreaks.trim();
    }

    public int contentLength(String request) {
        String newRequest = request.trim();
        String[] ary = newRequest.split("\n");
        for (String line : ary) {
           String[] wordAry = line.split(" ");
            if (wordAry[0].equals("Content-Length:")) {
                return Integer.parseInt(wordAry[1]);
            }
        }
        return 0;
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

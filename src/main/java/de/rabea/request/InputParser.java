package de.rabea.request;

public class InputParser {

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
}

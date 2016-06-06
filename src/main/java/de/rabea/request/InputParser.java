package de.rabea.request;

public class InputParser {

    public int contentLength(String request) {
        String[] lines = request.trim().split("\n");
        for (String line : lines) {
            Integer length = length(line);
            if (length != null) return length;
        }
        return 0;
    }

    public boolean hasBody(String request) {
        return contentLength(request) > 0;
    }

    private Integer length(String line) {
        String[] words = line.split(" ");
        if (words[0].equals("Content-Length:")) {
            return Integer.parseInt(words[1]);
        }
        return null;
    }
}

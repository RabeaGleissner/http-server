package de.rabea;

public enum HttpVerb {
    GET,
    POST,
    PUT,
    OPTIONS,
    HEAD;

    public static HttpVerb convert(String word) {
        if (word.equals("GET")) {
            return GET;
        } else if (word.equals("POST")) {
            return POST;
        } else if (word.equals("PUT")) {
            return PUT;
        } else if (word.equals("OPTIONS")) {
            return OPTIONS;
        } else if (word.equals("HEAD")) {
            return HEAD;
        }
        return null;
    }

    public static boolean isExisting(HttpVerb verb) {
        return verb == GET ||
               verb == POST ||
               verb == PUT ||
               verb == HEAD ||
               verb == OPTIONS;
    }
}


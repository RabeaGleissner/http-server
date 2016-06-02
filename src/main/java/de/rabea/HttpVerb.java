package de.rabea;

public enum HttpVerb {
    GET,
    POST,
    PUT,
    OPTIONS,
    HEAD,
    DELETE;

    public static HttpVerb convert(String word) {
        for (HttpVerb verb : HttpVerb.values()) {
            if (word.equals(verb.toString())) {
                return verb;
            }
        }
        return null;
    }
}

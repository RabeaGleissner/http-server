package de.rabea;

public enum HttpVerb {
    GET,
    POST,
    PUT,
    OPTIONS,
    HEAD;

    public static HttpVerb convert(String word) {
        HttpVerb[] verbs = HttpVerb.class.getEnumConstants();
        for (HttpVerb verb : verbs) {
            if (word.equals(verb.toString())) {
                return verb;
            }
        }
        return null;
    }
}

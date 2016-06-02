package de.rabea;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RequestSplitter {

    private final List<String> request;

    public RequestSplitter(String incoming) {
        this.request = split(incoming);
    }

    public HttpVerb httpVerb() {
        return HttpVerb.convert(request.get(0));
    }

    public String route() {
        String url = request.get(1);
        String route = removeParameters(url);
        return route;
    }

    private String removeParameters(String url) {
        int index = url.indexOf("?");
        if (index != -1) {
            return url.substring(0, index);
        } else {
            return url;
        }
    }

    public String body() {
        return request.get(request.size() -1);
    }

    private List<String> split(String incoming) {
        String[] lines = incoming.split("\n");
        List<String> words = new LinkedList<>() ;
        for (String line : lines) {
            String[] splitLine =  line.split(" ");
            Collections.addAll(words, splitLine);
        }
        return words;
    }
}

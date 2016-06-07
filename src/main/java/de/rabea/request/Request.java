package de.rabea.request;

import de.rabea.server.HttpVerb;
import de.rabea.server.Route;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Request {

    private List<String> wordList;
    private UrlParser urlParser;
    private String incoming;
    private Route route;

    public Request(String incoming) {
        this.incoming = incoming;
        this.route = new Route();
        this.wordList = split();
        this.urlParser = new UrlParser(url());
    }

    public Request() {
    }

    public HttpVerb httpVerb() {
        return HttpVerb.convert(wordList.get(0));
    }

    public String url() {
        return wordList.get(1);
    }

    public String route() {
        return urlParser.route();
    }

    public String body() {
        if (new InputParser().hasBody(incoming)) {
            return wordList.get(wordList.size() -1);
        } else {
            return "";
        }
    }

    public boolean hasBody() {
        return !body().equals("");
    }

    public String urlParams() {
        return urlParser.parameters();
    }

    public boolean hasUrlParams() {
        return urlParser.hasParams();
    }

    public boolean requestsPartialContent() {
        return wordList.indexOf("Range:") != -1;
    }

   public boolean isTeapot() {
        return route.isTeaRoute(route());
    }

    public boolean isRedirect() {
        return route.isRedirect(route());
    }

    private List<String> split() {
        String[] lines = incoming.split("\n");
        List<String> words = new LinkedList<>() ;
        for (String line : lines) {
            String[] splitLine =  line.split(" ");
            Collections.addAll(words, splitLine);
        }
        return words;
    }

    public String range() {
        String range = wordList.get(wordList.indexOf("Range:") + 1);
        return range.substring(range.indexOf("=") + 1);
    }
}

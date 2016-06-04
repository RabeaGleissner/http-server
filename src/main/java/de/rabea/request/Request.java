package de.rabea.request;

import de.rabea.server.ContentStorage;
import de.rabea.server.HttpVerb;
import de.rabea.server.Resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Request {

    private List<String> wordList;
    private UrlParser urlParser;
    private String incoming;
    private ContentStorage contentStorage;
    private String directory;

    public Request(String incoming, ContentStorage contentStorage, String directory) {
        this.incoming = incoming;
        this.contentStorage = contentStorage;
        this.directory = directory;
        this.wordList = split();
        this.urlParser = new UrlParser(url());
        updateContentStorage();
    }

    public Request() {
    }

    public HttpVerb httpVerb() {
        return HttpVerb.convert(wordList.get(0));
    }

    private String url() {
        return wordList.get(1);
    }

    public String route() {
        return urlParser.route();
    }

    public String body() {
        if (new InputParser().hasBody(incoming)) {
            return wordList.get(wordList.size() -1);
        } else if (urlParser.hasParams()) {
            return urlParams();
        } else {
            return "";
        }
    }

    public String urlParams() {
        return urlParser.parameters();
    }

    private void updateContentStorage() {
        if (!body().equals("")) {
            contentStorage.save(route(), body());
        }

        if (httpVerb() == HttpVerb.DELETE) {
            contentStorage.deleteFor(route());
        }

        Resource resource = new Resource();
        if (resource.isInPublicDir(resource.file(route()), directory)) {
            String fileContent;
            if (isPartial()) {
                fileContent = new FileParser(directory + route(), range()).read();
            } else {
                fileContent = new FileParser(directory + route()).read();
            }
            contentStorage.save(route(), fileContent);
        }
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

    public boolean isPartial() {
        return wordList.indexOf("Range:") != -1;
    }

    private int[] range() {
        int rangeIndex = wordList.indexOf("Range:");
        String range = wordList.get(rangeIndex + 1);
        List<String> rangeLetters = Arrays.asList(range.split(""));
        int index = rangeLetters.indexOf("=");
        int start = Integer.parseInt(rangeLetters.get(index + 1));
        int end = Integer.parseInt(rangeLetters.get(index + 3));
        int[] returnRange = {start, end + 1};

        return returnRange;
    }
}
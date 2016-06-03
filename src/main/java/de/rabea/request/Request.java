package de.rabea.request;

import de.rabea.server.ContentStorage;
import de.rabea.server.HttpVerb;
import de.rabea.server.Resource;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Request {

    private final List<String> wordList;
    private final UrlParser urlParser;
    private String incoming;
    private ContentStorage contentStorage;
    private String directory;

    public Request(String incoming, ContentStorage contentStorage, String directory) {
        this.incoming = incoming;
        this.contentStorage = contentStorage;
        this.directory = directory;
        this.wordList = split(incoming);
        this.urlParser = new UrlParser(url());
        updateContentStorage();
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
            String fileContent = new FileParser(directory + route()).read();
            contentStorage.save(route(), fileContent);
        }
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

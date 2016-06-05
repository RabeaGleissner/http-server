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

    public byte[] body() {
        if (new InputParser().hasBody(incoming)) {
            return wordList.get(wordList.size() -1).getBytes();
        } else if (urlParser.hasParams()) {
            return urlParams().getBytes();
        } else {
            return new byte[0];
        }
    }

    public String urlParams() {
        return urlParser.parameters();
    }

    public boolean isPartial() {
        return wordList.indexOf("Range:") != -1;
    }

    private void updateContentStorage() {
        if (hasBody()) {
            contentStorage.save(route(), body());
        }

        if (deleteRequest()) {
            contentStorage.deleteFor(route());
        }

        Resource resource = new Resource();
        if (resource.isInPublicDir(resource.file(route()), directory)) {
            byte[] fileContent;
            if (isPartial()) {
                fileContent = new FileParser(directory + route(), range()).read();
            } else {
                fileContent = new FileParser(directory + route()).read();
            }
            contentStorage.save(route(), fileContent);
        }
    }

    private boolean deleteRequest() {
        return httpVerb() == HttpVerb.DELETE;
    }

    private boolean hasBody() {
        return !Arrays.equals(body(), new byte[0]);
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

    private String range() {
        String range = wordList.get(wordList.indexOf("Range:") + 1);
        return range.substring(range.indexOf("=") + 1);
    }
}

package de.rabea;

import de.rabea.server.ContentStorage;
import de.rabea.server.InputParser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Request {

    private final List<String> request;
    private String incoming;
    private ContentStorage contentStorage;

    public Request(String incoming, ContentStorage contentStorage) {
        this.incoming = incoming;
        this.contentStorage = contentStorage;
        this.request = split(incoming);
        updateContentStorage();
    }

    public HttpVerb httpVerb() {
        return HttpVerb.convert(request.get(0));
    }

    public String route() {
        String url = request.get(1);
        return removeParameters(url);
    }

    public String parameterString() {
        String url = request.get(1);
        int index = url.indexOf("?");
        if (index != -1) {
            return url.substring(index);
        } else {
            return "";
        }
    }

    public String body() {
        if (new InputParser().hasBody(incoming)) {
            return request.get(request.size() -1);
        } else if (!parameterString().equals("")) {
            return urlParams();
        } else {
            return "";
        }
    }

    public String urlParams() {
        String params = parameterString();
        String decoded;
        try {
            decoded = URLDecoder.decode(getFirstVariable(params), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported");
        }
        return decoded + "\n" + getSecondVariable(params);
    }

    private void updateContentStorage() {
        if (!body().equals("")) {
            contentStorage.save(route(), body());
        }

        if (httpVerb() == HttpVerb.DELETE) {
            contentStorage.deleteFor(route());
        }
    }

    private String getSecondVariable(String parameters) {
        return parameters.substring(parameters.indexOf("&") + 1).replace("=", " = ");
    }

    private String getFirstVariable(String parameters) {
        return parameters.substring(1, parameters.indexOf("&")).replace("=", " = ");
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

    private String removeParameters(String url) {
        int index = url.indexOf("?");
        if (index != -1) {
            return url.substring(0, index);
        } else {
            return url;
        }
    }
}

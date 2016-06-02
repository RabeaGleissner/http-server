package de.rabea;

import de.rabea.server.InputParser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RequestSplitter {

    private final List<String> request;
    private String incoming;

    public RequestSplitter(String incoming) {
        this.incoming = incoming;
        this.request = split(incoming);
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
        } else if (parameterString() != "") {
            return urlParams();
        } else {
            return "";
        }
    }

    public String urlParams() {
        String params = parameterString();
        String decoded = "";
        try {
            decoded = URLDecoder.decode(getFirstVariable(params), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decoded + "\n" + getSecondVariable(params);
    }

    private String getSecondVariable(String parameters) {
        String onlySecond = parameters.substring(parameters.indexOf("&") + 1).replace("=", " = ");
        return onlySecond;
    }

    private String getFirstVariable(String parameters) {
        String onlyFirst = parameters.substring(1, parameters.indexOf("&")).replace("=", " = ");
        return onlyFirst;
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

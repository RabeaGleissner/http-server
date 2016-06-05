package de.rabea.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlParser {

    private final String url;

    public UrlParser(String url) {
        this.url = url;
    }

    public String parameterString() {
        int index = url.indexOf("?");
        if (index != -1) {
            return url.substring(index);
        } else {
            return "";
        }
    }

    public String route() {
        return removeParameters();
    }

    private String removeParameters() {
        int index = url.indexOf("?");
        if (index != -1) {
            return url.substring(0, index);
        } else {
            return url;
        }
    }

    public String parameters() {
        String params = parameterString();
        String decoded;
        try {
            decoded = URLDecoder.decode(getFirstVariable(params), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported");
        }
        return decoded + "\n" + getSecondVariable(params);
    }

    private String getSecondVariable(String parameters) {
        return parameters.substring(parameters.indexOf("&") + 1).replace("=", " = ");
    }

    private String getFirstVariable(String parameters) {
        return parameters.substring(1, parameters.indexOf("&")).replace("=", " = ");
    }

    public boolean hasParams() {
        return !parameterString().equals("");
    }
}

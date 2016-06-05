package de.rabea.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlParser {

    private final String url;
    private String parameterString;

    public UrlParser(String url) {
        this.url = url;
        this.parameterString = parameterString();
    }

    public String parameters() {
        if (hasParams()) {
            String second;
            if (hasMoreThanOne()) {
               second = secondParameter();
            } else {
                second = "";
            }
            try {
                return URLDecoder.decode(concatenate(firstParameter()+ "\n" + second), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError("UTF-8 not supported");
            }
        } else {
            return "";
        }
    }

    private String concatenate(String ... params) {
        String concatenated = "";
        for (String param : params) {
            concatenated += param;
        }
        return concatenated;
    }

    private String firstParameter() {
        if (hasMoreThanOne()) {
            return parameterString.substring(1, parameterString.indexOf("&")).replace("=", " = ");
        } else {
            return parameterString.substring(1).replace("=", " = ");
        }
    }

    private String secondParameter() {
        return parameterString.substring(parameterString.indexOf("&") + 1).replace("=", " = ");
    }

    private boolean hasMoreThanOne() {
        return parameterString().contains("&");
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


    public boolean hasParams() {
        return !parameterString().equals("");
    }
}

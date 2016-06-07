package de.rabea.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UriParser {

    private final String url;
    private final String parameterString;

    public UriParser(String url) {
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
                return URLDecoder.decode(firstParameter()+ "\n" + second, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError("UTF-8 not supported");
            }
        } else {
            return "";
        }
    }

    public String route() {
        return removeParameters();
    }

    public boolean hasParams() {
        return !parameterString().equals("");
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

    private String parameterString() {
        int index = url.indexOf("?");
        if (index != -1) {
            return url.substring(index);
        } else {
            return "";
        }
    }

    private String removeParameters() {
        int index = url.indexOf("?");
        if (index != -1) {
            return url.substring(0, index);
        } else {
            return url;
        }
    }

}

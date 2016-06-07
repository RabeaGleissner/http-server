package de.rabea.server;

public enum Resource {
    ROOT("/"),
    FORM("/form"),
    METHOD("/method_options"),
    METHOD2("/method_options2"),
    PARAMS("/parameters"),
    REDIRECT("/redirect"),
    TEA("/tea");

    private final String url;

    Resource(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
}

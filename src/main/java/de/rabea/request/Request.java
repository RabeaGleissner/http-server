package de.rabea.request;

import de.rabea.server.HttpVerb;
import de.rabea.server.Router;

import java.util.*;

public class Request {

    public String body;
    public String uri;
    public HttpVerb httpVerb;
    public String route;
    public String range;
    public String urlParams;
    public Directory directory;
    public String authorisation;
    private List<String> components;
    private UriParser uriParser;
    private String incoming;
    private Router router;

    public Request(String incoming, Directory directory) {
        this.incoming = incoming;
        this.directory = directory;
        this.components = split();
        this.router = new Router(directory);
        this.uriParser = new UriParser(uri());
        this.httpVerb = httpVerb();
        this.uri = uri();
        this.body = body();
        this.route = route();
        this.range = range();
        this.urlParams = urlParams();
        this.authorisation = authorisation();
    }

    public Request() {
    }

    public boolean hasBody() {
        return !body.equals("");
    }

    public boolean knownUri() {
        return router.isExisting(uri);
    }

    public boolean hasUrlParams() {
        return uriParser.hasParams();
    }

    public boolean requestsPartialContent() {
        return components.indexOf("Range:") != -1;
    }

    public boolean isTeapot() {
        return router.isTeaRoute(route);
    }

    public boolean isRedirect() {
        return router.isRedirect(route);
    }

    private HttpVerb httpVerb() {
        return HttpVerb.convert(components.get(0));
    }


    private String route() {
        return uriParser.route();
    }

    private String body() {
        if (new InputParser().hasBody(incoming)) {
            return components.get(components.size() -1);
        } else {
            return "";
        }
    }

    private String urlParams() {
        return uriParser.parameters();
    }

    private String range() {
        String range = components.get(components.indexOf("Range:") + 1);
        return range.substring(range.indexOf("=") + 1);
    }

    private String uri() {
        return components.get(1);
    }

    private List<String> split() {
        List<String> lines = splitIntoLines();
        List<String> words = new LinkedList<>();
        for (String line : lines) {
            String[] splitLine =  line.split(" ");
            Collections.addAll(words, splitLine);
        }
        return words;
    }

    private List<String> splitIntoLines() {
        String[] lines = incoming.split("\n");
        return Arrays.asList(lines);
    }

    private String authorisation() {
        return components.get(components.indexOf("Authorization:") + 1) + " " + components.get(components.indexOf("Authorization:") + 2);
    }

}

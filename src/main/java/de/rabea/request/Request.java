package de.rabea.request;

import de.rabea.server.HttpVerb;
import de.rabea.server.Router;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static de.rabea.server.HttpVerb.GET;
import static de.rabea.server.HttpVerb.convert;

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
    private final String AUTHORISATION_KEY = "Basic YWRtaW46aHVudGVyMg==";

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

    public String head() {
        return splitIntoLines().get(0);
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

    public String eTag() {
        return findValueFor("If-Match:");
    }

    public boolean isPatch() {
        return httpVerb == HttpVerb.PATCH;
    }

    public boolean hasCorrectETag() {
        Sha1Encoder sha1Encoder = new Sha1Encoder(asString(readFile()));
        return eTag().equals(sha1Encoder.computeSha1());
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

    public boolean notAuthorised() {
        return authorisation.equals("");
    }

    public boolean isAuthorised() {
        return authorisation.equals(AUTHORISATION_KEY);
    }

    public boolean asksForLogs() {
        return httpVerb == GET && route.equals("/logs");
    }

    private HttpVerb httpVerb() {
        return convert(components.get(0));
    }

    private String route() {
        return uriParser.route();
    }

    private String body() {
        if (new InputParser().hasBody(incoming)) {
            List<String> lines = splitIntoLines();
            return lines.get(lines.size() - 1);
        } else {
            return "";
        }
    }

    private String urlParams() {
        return uriParser.parameters();
    }

    private String range() {
        return findValueFor("Range:");
    }

    private String findValueFor(String key) {
        String value = components.get(components.indexOf(key) + 1);
        return value.substring(value.indexOf("=") + 1);

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
        int index = components.indexOf("Authorization:");
        if (index != -1) {
            return components.get(index + 1) + " " + components.get(index + 2);
        }
        return "";
    }

    private byte[] readFile() {
        return new FileParser(directory.path + uri).read();
    }

    private static String asString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}

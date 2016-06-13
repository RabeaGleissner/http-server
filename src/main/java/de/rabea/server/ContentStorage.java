package de.rabea.server;

import de.rabea.request.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static de.rabea.server.HttpVerb.*;

public class ContentStorage {
    private final Map<String, byte[]> storage;
    private final Log log;
    private final String PROTOCOL = "HTTP/1.1\n";

    public ContentStorage() {
        this.storage = new HashMap<>();
        this.log = new Log();
    }

    public void update(String route, byte[] body, HttpVerb verb) {
        if (responseBody(body)) {
            save(route, body);
        }

        if (deleteRequest(verb)) {
            deleteFor(route);
        }

        saveActionInLog(route, verb);
    }

    private void saveActionInLog(String route, HttpVerb verb) {
        log.register(verb + route + PROTOCOL);
    }

    public void save(String url, byte[] body) {
        storage.put(url, body);
    }

    public byte[] bodyFor(String url) {
        byte[] content = storage.get(url);
        if (content != null) {
            return content;
        } else {
            return new byte[0];
        }
    }

    private void deleteFor(String url) {
        storage.remove(url);
    }

    private boolean responseBody(byte[] body) {
        return !Arrays.equals(body, new byte[0]);
    }

    private boolean deleteRequest(HttpVerb verb) {
        return verb == DELETE;
    }
}

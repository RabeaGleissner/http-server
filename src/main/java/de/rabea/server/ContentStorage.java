package de.rabea.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContentStorage {
    private final Map<String, byte[]> storage;

    public ContentStorage() {
        this.storage = new HashMap<>();
    }

    public void update(String route, byte[] body, HttpVerb verb) {
        if (!Arrays.equals(body, new byte[0])) {
            save(route, body);
        }

        if (verb == HttpVerb.DELETE) {
            deleteFor(route);
        }
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

    public void deleteFor(String url) {
        storage.remove(url);
    }
}

package de.rabea.server;

import java.util.HashMap;
import java.util.Map;

public class ContentHolder {
    private Map<String, String> storage;

    public ContentHolder() {
        System.out.println("new content holder!!");
        this.storage = new HashMap<>();

    }

    public void save(String url, String body) {
        storage.put(url, body);
    }

    public String getContentFor(String url) {
        String content = storage.get(url);
        if (content != null) {
            System.out.println("content = " + content);
            return content;
        } else {
            return "";
        }
    }

    public void deleteFor(String url) {
        storage.remove(url);
    }
}

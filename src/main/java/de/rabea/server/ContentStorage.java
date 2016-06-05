package de.rabea.server;

import de.rabea.request.FileParser;
import de.rabea.request.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContentStorage {
    private final Map<String, byte[]> storage;

    public ContentStorage() {
        this.storage = new HashMap<>();
    }

    public void update(Request request, String directory) {
        if (hasBody(request.body())) {
            save(request.route(), request.body());
        }

        if (request.deleteRequest()) {
            deleteFor(request.route());
        }

        Resource resource = new Resource();
        if (resource.isInDirectory(resource.file(request.route()), directory)) {
            byte[] fileContent;
            if (request.isPartial()) {
                fileContent = new FileParser(directory + request.route(), request.range()).read();
            } else {
                fileContent = new FileParser(directory + request.route()).read();
            }
            save(request.route(), fileContent);
        }

        if (resource.requestRoot(request.route()) && resource.directoryHasContent(directory)) {
            String files = "";
            for (String file : resource.directoryContents(directory)) {
                files += "<a href=/" + fileName(file) + ">" + fileName(file) + "</a>";
            }
            save(request.route(), files.getBytes());
        }
    }

    private String fileName(String file) {
        String[] folders = file.split("/");
        return folders[folders.length - 1];
    }

    private boolean hasBody(byte[] body) {
        return !Arrays.equals(body, new byte[0]);
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

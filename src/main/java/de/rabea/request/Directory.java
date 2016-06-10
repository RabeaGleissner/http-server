package de.rabea.request;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Directory {

    public final String path;
    public final List<String> contents;

    public Directory(String path) {
        this.path = path;
        this.contents = contents();
    }

    public boolean contains(String file) {
        return contents.contains(path + file);
    }

    public boolean containsContent() {
        File parentDirectory = new File(path);
        return parentDirectory.isDirectory() && parentDirectory.list().length > 0;
    }

    private List<String> contents() {
        List<String> files = new LinkedList<>();
        if (isDirectory(path)) {
            try {
                files = addFilesToList(files);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    private List<String> addFilesToList(List<String> files) throws IOException {
        Files.walk(Paths.get(path)).forEach(filePath -> {
            if (isRegularFile(filePath)) {
                files.add(String.valueOf(filePath));
            }
        });
        return files;
    }

    private boolean isRegularFile(Path filePath) {
        return Files.isRegularFile(filePath);
    }

    private boolean isDirectory(String directory) {
        File file = new File(directory);
        return file.isDirectory();
    }
}

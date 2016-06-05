package de.rabea.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Resource {
    private final List<String> allRoutes;

    public Resource() {
        allRoutes = allRoutes();
    }

    private List<String> allRoutes() {
        return Arrays.asList(
                "/",
                "/form",
                "/method_options",
                "/method_options2",
                "/parameters",
                "/redirect",
                "/tea"
        );
    }

    public boolean requestRoot(String route) {
        return route.equals("/");

    }

    public boolean isExisting(String route, String directory) {
        return allRoutes.contains(route) || isInDirectory(file(route), directory);
    }

    public boolean isTeaRoute(String route) {
        return route.equals("/coffee");
    }

    public String file(String route) {
        return route.substring(1);
    }

    public boolean isRedirect(String route) {
        return route.equals("/redirect");
    }

    public boolean isInDirectory(String file, String directory) {
        return directoryContents(directory).contains(directory + file);
    }

    public boolean directoryHasContent(String directory) {
        File parentDirectory = new File(directory);
        return parentDirectory.isDirectory() && parentDirectory.list().length > 0;
    }

    public List<String> directoryContents(String directory) {
        List<String> files = new LinkedList<>();
        try {
            Files.walk(Paths.get(directory)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(String.valueOf(filePath));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}

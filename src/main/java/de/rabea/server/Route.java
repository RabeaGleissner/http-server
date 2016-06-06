package de.rabea.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static de.rabea.server.HttpVerb.*;

public class Route {

    private final List<String> allResourcesOLD;
    private final Map<Resource, List<HttpVerb>> existingRoutes;

    public Route() {
        allResourcesOLD = allResources();
        existingRoutes = new RouteConfiguration().existingRoutes();
    }

    private List<String> allResources() {
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
        return allResourcesOLD.contains(route) || isInDirectory(file(route), directory);
    }

    public boolean isTeaRoute(String route) {
        return route.equals("/coffee");
    }

    public String file(String route) {
        if (route.length() > 1) {
            return route.substring(1);
        }
        return "";
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

    public boolean isDirectory(String directory) {
        File file = new File(directory);
        return file.isDirectory();
    }

    public List<String> directoryContents(String directory) {
        List<String> files = new LinkedList<>();
        if (isDirectory(directory)) {
            try {
                Files.walk(Paths.get(directory)).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        files.add(String.valueOf(filePath));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }
}

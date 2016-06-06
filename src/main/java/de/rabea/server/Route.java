package de.rabea.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Route {

    private final Map<String, List<HttpVerb>> existingRoutes;

    public Route() {
        existingRoutes = new RouteConfiguration().existingRoutes();
    }

    public boolean requestRoot(String route) {
        return route.equals("/");
    }

    public boolean isExisting(String route, String directory) {
        return existingRoutes.containsKey(route) || isInDirectory(file(route), directory);
    }

    public String optionsFor(String resource) {
        List<HttpVerb> actions = existingRoutes.get(resource);
        String availableActions = "";
        for (HttpVerb action : actions) {
            availableActions += action.toString() + ",";
        }
        return availableActions.substring(0, availableActions.length() - 1);
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

    private boolean isDirectory(String directory) {
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

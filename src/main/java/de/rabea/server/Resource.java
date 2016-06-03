package de.rabea.server;

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

    public boolean isExisting(String route, String directory) {
        return allRoutes.contains(route) || isInPublicDir(file(route), directory);
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

    public boolean isInPublicDir(String resource, String file) {
        List<String> files = new LinkedList<>();
        try {
            Files.walk(Paths.get(file)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(String.valueOf(filePath));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files.contains(file + resource);
    }
}

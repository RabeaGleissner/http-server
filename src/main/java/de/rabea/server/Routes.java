package de.rabea.server;

import java.util.Arrays;
import java.util.List;

public class Routes {
    private final List<String> allRoutes;

    public Routes() {
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

    public boolean isExisting(String route) {
        return allRoutes.contains(route);
    }

    public boolean isTeaRoute(String route) {
        return route.equals("/coffee");
    }

    public boolean isRedirect(String route) {
        return route.equals("/redirect");
    }
}

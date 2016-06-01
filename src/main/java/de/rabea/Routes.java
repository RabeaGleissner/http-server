package de.rabea;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Routes {
    private List<String> allRoutes;

    public Routes() {
        allRoutes = allRoutes();
    }

    private List<String> allRoutes() {
        return new LinkedList<String>(Arrays.asList(
                "/",
                "/form",
                "/method_options",
                "/method_options2"
        ));
    }

    public boolean isExisting(String route) {
        return allRoutes.contains(route);
    }
}

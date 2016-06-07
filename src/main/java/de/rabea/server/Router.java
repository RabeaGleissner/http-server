package de.rabea.server;

import de.rabea.request.Directory;

import java.util.List;
import java.util.Map;

public class Router {

    private final Map<String, List<HttpVerb>> existingRoutes;

    public Router() {
        existingRoutes = new RouteConfiguration().existingRoutes();
    }

    public boolean requestRoot(String route) {
        return route.equals("/");
    }

    public boolean isExisting(String route, String directory) {
        return existingRoutes.containsKey(route) || new Directory(directory).isInDirectory(route);
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

    public boolean isRedirect(String route) {
        return route.equals("/redirect");
    }
}

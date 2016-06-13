package de.rabea.server;

import de.rabea.request.Directory;

import java.util.List;
import java.util.Map;

import static de.rabea.server.HttpVerb.*;

public class Router {

    private final Map<String, List<HttpVerb>> existingRoutes;
    private final RouteConfiguration routeConfiguration;
    private final Directory directory;

    public Router(Directory directory) {
        this.routeConfiguration = new RouteConfiguration();
        this.existingRoutes = routeConfiguration.existingRoutes();
        this.directory = directory;
    }

    public boolean requestRoot(String route) {
        return route.equals("/");
    }

    public boolean routeNeedsAuthorisation(String route) {
        return routeConfiguration.needingAuthorisation().contains(route);
    }

    public boolean isExisting(String route) {
        return existingRoutes.containsKey(route) || directory.contains(route);
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

    public boolean validMethod(HttpVerb requestedAction, String uri) {
        if (isExisting(uri) && !directory.contains(uri)) {
            List<HttpVerb> allowedMethods = existingRoutes.get(uri);
            return (allowedMethods.contains(requestedAction));
        } else if (directory.contains(uri)) {
            return getRequestToFile(requestedAction, uri);
        }
        return false;
    }

    private boolean getRequestToFile(HttpVerb requestedAction, String uri) {
        return directory.contains(uri) && requestedAction == GET;
    }
}

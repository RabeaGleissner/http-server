package de.rabea.server;

import java.util.*;

import static de.rabea.server.HttpVerb.*;

public class RouteConfiguration {

    public Map<String, List<HttpVerb>> existingRoutes() {
        Map<String, List<HttpVerb>> existingRoutes = new HashMap<>();
        for (String resource : availableRoutes()) {
            List<HttpVerb> legalActions = associateActions(resource);
            existingRoutes.put(resource, legalActions);
        }
        return existingRoutes;
    }

    public List<String> needingAuthorisation() {
       List<String> routesNeedingAuth  = new LinkedList<>();
        routesNeedingAuth.add("/logs");
        return routesNeedingAuth;
    }

    private String[] availableRoutes() {
        return new String[]{
                "/",
                "/form",
                "/method_options",
                "/method_options2",
                "/parameters", "/redirect",
                "/tea",
                "/logs"
        };
    }

    private List<HttpVerb> associateActions(String resource) {
        List<HttpVerb> legalVerbs = new LinkedList<>();
        switch (resource) {
            case "/form" :
                legalVerbs.addAll(Arrays.asList(GET, POST, PUT, DELETE));
                break;
            case "/method_options":
                legalVerbs.addAll(Arrays.asList(GET, HEAD, POST, OPTIONS, PUT));
                break;
            case "/method_options2":
                legalVerbs.addAll(Arrays.asList(GET, OPTIONS));
                break;
            case "/":
                legalVerbs.addAll(Arrays.asList(GET, HEAD));
                break;
            default:
                legalVerbs.add(GET);
        }
        return legalVerbs;
    }
}

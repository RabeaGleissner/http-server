package de.rabea.server;

import java.util.*;

import static de.rabea.server.HttpVerb.*;

public class RouteConfiguration {

    public Map<String, List<HttpVerb>> existingRoutes() {
        Map<String, List<HttpVerb>> existingRoutes = new HashMap<>();
        for (Resource resource : Resource.values()) {
            List<HttpVerb> legalActions = associateActions(resource);
            existingRoutes.put(resource.url(), legalActions);
        }
        return existingRoutes;
    }

    private List<HttpVerb> associateActions(Resource resource) {
        List<HttpVerb> legalVerbs = new LinkedList<>();
        switch (resource) {
            case ROOT:
                legalVerbs.add(GET);
                break;
            case FORM:
                legalVerbs.addAll(Arrays.asList(GET, POST, PUT, DELETE));
                break;
            case METHOD:
                legalVerbs.addAll(Arrays.asList(GET, HEAD, POST, OPTIONS, PUT));
                break;
            case METHOD2:
                legalVerbs.addAll(Arrays.asList(GET, OPTIONS));
                break;
            case PARAMS:
                legalVerbs.add(GET);
                break;
            case REDIRECT:
                legalVerbs.add(GET);
                break;
            case TEA:
                legalVerbs.add(GET);
        }
        return legalVerbs;
    }


}

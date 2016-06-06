package de.rabea.server;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static de.rabea.server.HttpVerb.*;
import static org.junit.Assert.assertEquals;

public class RouteConfigurationTest {

    @Test
    public void returnsAvailableActionsForMethodsRoute() {
        RouteConfiguration configuration = new RouteConfiguration();
        Map<String, List<HttpVerb>> existingRoutes = configuration.existingRoutes();
        List<HttpVerb> verbs = new LinkedList<>();
        verbs.addAll(Arrays.asList(GET, HEAD, POST, OPTIONS, PUT));
        assertEquals(verbs, existingRoutes.get("/method_options"));
    }
}
package de.rabea.response;

public class Options implements HttpResponse {

    private final String route;

    public Options(String route) {
        this.route = route;
    }

    public String create() {
        if (route.equals("/method_options")) {
            return "HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT";
        } else if (route.equals("/method_options2")) {
            return "HTTP/1.1 200 OK\nAllow: GET,OPTIONS";
        }
        return null;
    }
}

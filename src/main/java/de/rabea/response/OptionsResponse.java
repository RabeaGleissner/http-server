package de.rabea.response;

public class OptionsResponse {

    private final String route;

    public OptionsResponse(String route) {
        this.route = route;
    }

    public String generate() {
        if (route.equals("/method_options")) {
            return "Allow: GET,HEAD,POST,OPTIONS,PUT";
        } else if (route.equals("/method_options2")) {
            return "Allow: GET,OPTIONS";
        }
        return null;
    }
}

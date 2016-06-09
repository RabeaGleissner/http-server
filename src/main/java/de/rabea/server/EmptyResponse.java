package de.rabea.server;

public class EmptyResponse implements Action {

    @Override
    public String response() {
        return "";
    }
}

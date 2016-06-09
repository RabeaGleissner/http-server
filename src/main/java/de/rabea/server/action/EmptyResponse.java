package de.rabea.server.action;

import de.rabea.server.Action;

public class EmptyResponse implements Action {

    @Override
    public byte[] response() {
        return "".getBytes();
    }
}

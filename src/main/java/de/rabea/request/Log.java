package de.rabea.request;

import java.util.LinkedList;
import java.util.List;

public class Log {

    private List<String> requests;
    public Log() {
        this.requests =  new LinkedList<>();
    }

    public void register(String requestHead) {
        requests.add(requestHead);
    }

    public String show() {
        String logs = "";
        for (String request : requests) {
           logs += request + "\n";
        }
        return logs;
    }
}

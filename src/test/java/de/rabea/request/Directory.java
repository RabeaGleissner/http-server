package de.rabea.request;

public class Directory {

    public String forResource() {
        return System.getProperty("user.dir") + "/src/test/java/de/rabea/request/resource/";
    }
}

package de.rabea;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Helper {

    public static String asString(byte[] bytes) {
        return new String(bytes, UTF_8);
    }
}

package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class Sha1EncoderTest {

    @Test
    public void convertsTextToSha1() {
        Sha1Encoder sha1Encoder = new Sha1Encoder("Some content");
        assertEquals("9f1a6ecf74e9f9b1ae52e8eb581d420e63e8453a", sha1Encoder.computeSha1());
    }
}
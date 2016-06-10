package de.rabea.request;

import de.rabea.exceptions.ShaComputingException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Encoder {

    final protected static char[] hexArray = "0123456789abcdef".toCharArray();
    private String text;

    public Sha1Encoder(String text) {
        this.text = text;
    }

    public String computeSha1() {
        try {
            return computeSha1OfByteArray(text.getBytes(("UTF-8")));
        } catch (UnsupportedEncodingException ex) {
            throw new ShaComputingException("Encoding not supported " + ex.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new ShaComputingException("This algorithm does not exist " + e.getMessage());
        }
    }

    private String computeSha1OfByteArray(final byte[] message) throws UnsupportedOperationException, NoSuchAlgorithmException {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(message);
            byte[] res = messageDigest.digest();
            return toHexString(res);
    }

    private String toHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
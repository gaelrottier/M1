package fr.unice.miage.m1.distributedsystems.tp4.chord;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Key extends Identifier {

    private static final long serialVersionUID = 1L;

    public Key(String value) {
        this(value.getBytes());
    }

    public Key(byte[] bytes) {
        super(toIntegerRestrictedToInterval(bytes, NB_BITS));
    }

    private static int toIntegerRestrictedToInterval(byte[] bytes,
                                                     int intervalSize) {
        return (new BigInteger(computeSHA1(bytes)).abs().intValue()
                % intervalSize + intervalSize)
                % intervalSize;
    }

    private static byte[] computeSHA1(byte[] bytes) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return md.digest(bytes);
    }

}

package es.gob.valet.utils;

import java.security.SecureRandom;
import java.util.Random;

public class SecureRandomXmlIdGenerator {
    private static final char[] CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static final int DEFAULT_LENGTH = 16;
    private final Random random;

    public static final SecureRandomXmlIdGenerator INSTANCE = new SecureRandomXmlIdGenerator();

    private SecureRandomXmlIdGenerator() {
        this.random = new SecureRandom();
    }

    public String generateIdentifier() {
        return generateIdentifier(DEFAULT_LENGTH);
    }

    public String generateIdentifier(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS[random.nextInt(CHARACTERS.length)]);
        }
        return sb.toString();
    }
}
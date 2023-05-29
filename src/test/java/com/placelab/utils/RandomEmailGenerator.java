package com.placelab.utils;

import java.util.UUID;

public class RandomEmailGenerator {
    private static final String[] DOMAIN_OPTIONS = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com"};

    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        String domain = getRandomDomain();
        return uuid + "@" + domain;
    }

    private static String getRandomDomain() {
        int randomIndex = (int) (Math.random() * DOMAIN_OPTIONS.length);
        return DOMAIN_OPTIONS[randomIndex];
    }
}

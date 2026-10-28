package com.app.randompassword.services;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final String ALL = UPPERCASE + LOWERCASE + DIGITS + SPECIALS;

    private static SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("La longueur minimale recommandée est 8 caractères.");
        }

        StringBuilder password = new StringBuilder(length);
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIALS.charAt(random.nextInt(SPECIALS.length())));

        for (int i = 4; i < length; i++) {
            password.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        return shuffleString(password.toString());
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int j = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
}


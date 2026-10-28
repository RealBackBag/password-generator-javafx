package com.app.randompassword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    @DisplayName("Devrait générer un mot de passe de la bonne longueur")
    void testPasswordLength() {
        int expectedLength = 14;
        String password = PasswordGenerator.generatePassword(expectedLength);

        assertNotNull(password, "Le mot de passe ne doit pas être nul");
        assertEquals(expectedLength, password.length(), "La longueur générée doit correspondre à la demande");
    }

    @Test
    @DisplayName("Devrait lever une exception si la longueur est inférieure à 8")
    void testInvalidPasswordLength() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PasswordGenerator.generatePassword(5);
        });

        assertTrue(exception.getMessage().contains("8 caractères"));
    }

    @Test
    @DisplayName("Devrait contenir au moins une majuscule, une minuscule, un chiffre et un caractère spécial")
    void testPasswordComplexity() {
        String password = PasswordGenerator.generatePassword(16);

        assertTrue(password.matches(".*[A-Z].*"), "Doit contenir au moins une majuscule");
        assertTrue(password.matches(".*[a-z].*"), "Doit contenir au moins une minuscule");
        assertTrue(password.matches(".*[0-9].*"), "Doit contenir au moins un chiffre");
        assertTrue(password.matches(".*[!@#$%^&*()\\-_=+\\[\\]{}|;:,.<>?].*"), "Doit contenir au moins un caractère spécial");
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 12, 16, 24, 32})
    @DisplayName("Devrait générer correctement des mots de passe de différentes longueurs valides")
    void testMultipleLengths(int length) {
        String password = PasswordGenerator.generatePassword(length);
        assertEquals(length, password.length());
    }
}
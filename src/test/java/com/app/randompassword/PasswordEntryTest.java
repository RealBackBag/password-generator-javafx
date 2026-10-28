package com.app.randompassword;

import com.app.randompassword.services.PasswordEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEntryTest {

    private PasswordEntry entry;

    @BeforeEach
    void setUp() {
        entry = new PasswordEntry("GitHub", "P@ssw0rd123!", true);
    }

    @Test
    @DisplayName("Devrait initialiser l'entrée avec l'état disponible")
    void testInitialState() {
        assertEquals("GitHub", entry.getName());
        assertEquals("P@ssw0rd123!", entry.getPassword());
        assertTrue(entry.isAvailable(), "Le mot de passe doit être disponible par défaut");
    }

    @Test
    @DisplayName("Devrait basculer l'état à indisponible après une copie")
    void testStateChangeAfterCopy() {
        entry.setAvailable(false);
        assertFalse(entry.isAvailable(), "Le mot de passe ne doit plus être disponible après modification de l'état");
    }

    @Test
    @DisplayName("Devrait formater correctement pour la sauvegarde en base de données")
    void testToDatabaseFormat() {
        String expectedFormatAvailable = "GitHub | P@ssw0rd123! | Disponible";
        assertEquals(expectedFormatAvailable, entry.toDatabaseFormat());

        entry.setAvailable(false);
        String expectedFormatUnavailable = "GitHub | P@ssw0rd123! | Copie / Non Disponible";
        assertEquals(expectedFormatUnavailable, entry.toDatabaseFormat());
    }
}
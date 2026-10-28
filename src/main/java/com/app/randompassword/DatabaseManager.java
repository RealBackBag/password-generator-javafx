package com.app.randompassword;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String FILE_NAME = "passwords_db.txt";

    // Enregistre toute la liste d'historique dans le fichier texte
    public static void savePasswords(List<PasswordEntry> history) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (PasswordEntry entry : history) {
                writer.write(entry.toDatabaseFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    // Charge les mots de passe existants au démarrage (Optionnel mais recommandé)
    public static List<PasswordEntry> loadPasswords() {
        List<PasswordEntry> list = new ArrayList<>();
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 3) {
                    boolean available = parts[2].equals("Disponible");
                    list.add(new PasswordEntry(parts[0], parts[1], available));
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
        }
        return list;
    }
}


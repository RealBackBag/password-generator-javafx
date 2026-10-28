package com.app.randompassword.controllers;

import com.app.randompassword.models.DatabaseManager;
import com.app.randompassword.services.PasswordEntry;
import com.app.randompassword.services.PasswordGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.Timer;
import java.util.TimerTask;

public class PasswordController {
    @FXML private TextField nameField;
    @FXML private TextArea passwordArea;
    @FXML private TableView<PasswordEntry> tableView;
    @FXML private TableColumn<PasswordEntry, String> nameColumn;
    @FXML private TableColumn<PasswordEntry, String> passwordColumn;
    @FXML private TableColumn<PasswordEntry, Boolean> availableColumn;

    private final ObservableList<PasswordEntry> history = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Liaison des colonnes
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());

        // Masquage visuel des mots de passe dans le tableau (Sécurité)
        passwordColumn.setCellValueFactory(cell -> cell.getValue().passwordProperty());
        passwordColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("••••••••••••"); // Affichage masqué
                }
            }
        });

        // Personnalisation de la colonne 'Disponible' avec du style CSS
        availableColumn.setCellValueFactory(cell -> cell.getValue().availableProperty());
        availableColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else if (item) {
                    setText("Disponible");
                    setStyle("-fx-text-fill: #2e7d32; -fx-font-weight: bold;");
                } else {
                    setText("Copie / Utilise");
                    setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                }
            }
        });

        // Chargement initial
        history.addAll(DatabaseManager.loadPasswords());
        tableView.setItems(history);
    }

    @FXML
    public void generatePassword() {
        String name = nameField.getText().trim().isEmpty() ? "Sans nom" : nameField.getText().trim();

        // FIX : Utilisation directe du PasswordGenerator sécurisé (longueur 14)
        String pwd = PasswordGenerator.generatePassword(14);
        passwordArea.setText(pwd);

        PasswordEntry newEntry = new PasswordEntry(name, pwd, true);
        history.add(0, newEntry); // Ajout en haut du tableau

        DatabaseManager.savePasswords(history);
        nameField.clear();
    }

    @FXML
    public void copyPassword() {
        PasswordEntry selected = tableView.getSelectionModel().getSelectedItem();

        if (selected != null && selected.isAvailable()) {
            // 1. Copie dans le presse-papiers
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selected.getPassword());
            clipboard.setContent(content);

            // 2. Mise à jour de l'état (le Data Binding rafraîchit la vue automatiquement)
            selected.setAvailable(false);

            // 3. Persistance
            DatabaseManager.savePasswords(history);

            // 4. Effacement automatique du presse-papier après 30 secondes pour sécurité
            scheduleClipboardClear(selected.getPassword());

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Mot de passe copié ! Il sera effacé du presse-papiers dans 30 secondes.");

        } else {
            String message = (selected == null)
                    ? "Veuillez sélectionner un mot de passe dans le tableau."
                    : "Erreur : Ce mot de passe a déjà été copié et ne peut plus être récupéré.";
            showAlert(Alert.AlertType.ERROR, "Action impossible", message);
        }
    }

    private void scheduleClipboardClear(String copiedPassword) {
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                if (copiedPassword.equals(clipboard.getString())) {
                    clipboard.clear();
                }
            }
        }, 30000); // 30 secondes
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
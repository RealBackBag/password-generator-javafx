package com.app.randompassword;

import javafx.beans.property.*;

public class PasswordEntry {
    private final StringProperty name;
    private final StringProperty password;
    private final BooleanProperty available;

    public PasswordEntry(String name, String password, boolean available) {
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.available = new SimpleBooleanProperty(available);
    }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getPassword() { return password.get(); }
    public StringProperty passwordProperty() { return password; }

    public boolean isAvailable() { return available.get(); }
    public BooleanProperty availableProperty() { return available; }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    public String toDatabaseFormat() {
        return getName() + " | " + getPassword() + " | " + (isAvailable() ? "Disponible" : "Copie / Non Disponible");
    }
}
# 🔐 Secure Password Generator (JavaFX)

Une application de bureau moderne et sécurisée développée en Java 21 et JavaFX pour la génération, la gestion et la persistance locale de mots de passe robustes.

---

## ✨ Fonctionnalités Principales

* **Génération Sécurisée :** Utilisation de `SecureRandom` pour garantir un haut niveau d'entropie dans la création de mots de passe aléatoires.
* **Protection du Presse-papier (Copie Unique) :** Restriction de copie unique avec gestion visuelle de l'état pour empêcher les fuites accidentelles de secrets.
* **Persistance Locale :** Sauvegarde et gestion locale des entrées via un gestionnaire de stockage dédié (`DatabaseManager`).
* **Architecture Moderne :** Interface réactive exploitant le binding et les propriétés JavaFX.

---

## 🛠️ Stack Technique

* **Langage :** Java 21 (LTS)
* **Framework UI :** JavaFX 21 (avec FXML et CSS)
* **Système de Build :** Maven
* **Tests Unitaires :** JUnit 5
* **Gestion des Modules :** Java Platform Module System (`module-info.java`)

---

## 📁 Structure du Projet

```text
password-generator-javafx/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/app/randompassword/
│   │   │   │   ├── controllers/      # Contrôleurs FXML
│   │   │   │   ├── models/           # Modèles de données
│   │   │   │   ├── services/         # Logique métier et sécurité
│   │   │   │   └── PasswordApp.java  # Point d'entrée de l'application
│   │   │   └── module-info.java      # Configuration des modules Java 9+
│   │   └── resources/                # Vues FXML et styles CSS
│   └── test/
│       └── java/                     # Tests unitaires (JUnit 5)
├── pom.xml                           # Configuration Maven et dépendances
└── README.md
```

---

## 🚀 Installation & Lancement

### Prérequis

* JDK 21 ou supérieur installé.
* Maven 3.8+ configuré.

### 1.Cloner le dépôt

```bash
# Clonage du dépôt
git clone https://github.com/RealBackBag/password-generator-javafx.git

# Ouverture de dossier
cd password-generator-javafx
```

### 2. Exécuter les tests unitaires

```bash
# test avec Maven
mvn test
```

### 3. Lancer l'application

```bash
# toujours sur Maven
mvn javafx:run
```

module com.app.randompassword {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.randompassword to javafx.fxml;
    exports com.app.randompassword;
    exports com.app.randompassword.controllers;
    opens com.app.randompassword.controllers to javafx.fxml;
    exports com.app.randompassword.models;
    opens com.app.randompassword.models to javafx.fxml;
    exports com.app.randompassword.services;
    opens com.app.randompassword.services to javafx.fxml;
}
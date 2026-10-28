module com.app.randompassword {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.randompassword to javafx.fxml;
    exports com.app.randompassword;
}
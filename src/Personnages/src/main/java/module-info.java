module com.example.personnages {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.personnages to javafx.fxml;
    exports com.example.personnages;
}
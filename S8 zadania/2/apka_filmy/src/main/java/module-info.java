module com.example.apka_filmy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apka_filmy to javafx.fxml;
    exports com.example.apka_filmy;
}
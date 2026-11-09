module com.example.ucet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ucet to javafx.fxml;
    exports com.example.ucet;
}
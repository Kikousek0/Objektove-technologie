module com.example.evidencia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.evidencia to javafx.fxml;
    exports com.example.evidencia;
}
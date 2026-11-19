module com.example.apka_faktury {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.apka_faktury to javafx.fxml;
    exports com.example.apka_faktury;
}
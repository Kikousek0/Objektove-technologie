module com.example.zvierata {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zvierata to javafx.fxml;
    exports com.example.zvierata;
}
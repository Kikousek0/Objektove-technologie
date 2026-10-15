module com.example.animal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.animal to javafx.fxml;
    exports com.example.animal;
}
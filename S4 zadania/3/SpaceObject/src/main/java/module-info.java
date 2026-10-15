module com.example.spaceobject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.spaceobject to javafx.fxml;
    exports com.example.spaceobject;
}
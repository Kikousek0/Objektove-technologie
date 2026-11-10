module com.example.zoznam {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zoznam to javafx.fxml;
    exports com.example.zoznam;
}
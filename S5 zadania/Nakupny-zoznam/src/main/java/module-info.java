module com.example.nakupnyzoznam {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nakupnyzoznam to javafx.fxml;
    exports com.example.nakupnyzoznam;
}
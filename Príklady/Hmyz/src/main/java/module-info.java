module com.example.hmyz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hmyz to javafx.fxml;
    exports com.example.hmyz;
}
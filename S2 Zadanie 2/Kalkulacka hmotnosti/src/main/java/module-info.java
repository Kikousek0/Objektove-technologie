module com.example.kalkulackahmotnosti {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kalkulackahmotnosti to javafx.fxml;
    exports com.example.kalkulackahmotnosti;
}
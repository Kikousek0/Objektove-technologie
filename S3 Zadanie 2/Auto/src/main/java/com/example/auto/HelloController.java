package com.example.auto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane root;
    @FXML public void initialize() {
        Auto auto = new Auto();
        root.getChildren().add(auto);
    }
}

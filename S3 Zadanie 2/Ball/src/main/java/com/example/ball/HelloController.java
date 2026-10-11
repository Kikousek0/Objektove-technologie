package com.example.ball;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane root;

    @FXML  public void initialize() {
        Ball ball = new Ball();
        root.getChildren().add(ball);
        ball.requestFocus();
    }
}

package com.example.zvierata;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ryba extends Zvierata {
    private double dx = 2;
    private Circle kruh;

    public Ryba(double x, double y, Pane rootPane) {
        super(x,y,rootPane);
    }

    @Override
    public void createShape() {
        kruh = new Circle(25);
        kruh.setFill(Color.BLUE);
        this.shape = kruh;
    }

    @Override
    public void move() {
        if(isPaused) return;
        x += dx;
        double maxX = rootPane.getPrefWidth() - 20;
        if(x <= 0 || x >= maxX) {
            dx = -dx;
        }
        updateVisualPosition();
    }

    public void changeColor() {
        kruh.setFill(Color.RED);
    }
}

package com.example.hmyz;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Chrobak extends Hmyz{
    private double dx = 2.0;
    private Rectangle rect;

    public Chrobak(double x, double y, Pane rootPane) {
        super(x,y,rootPane);
    }
    @Override
    protected void createShape() {
        rect = new Rectangle(20,20);
        rect.setFill(Color.GREEN);
        this.shape = rect;
    }

    @Override
    public void move() {
        x += dx;

        double maxX = rootPane.getPrefWidth() - 25;

        if (x <= 0 || x >= maxX) {
            dx = -dx;
        }

        updateVisualPosition();
    }
}

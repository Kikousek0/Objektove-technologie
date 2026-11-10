package com.example.zvierata;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Krava extends Zvierata {
    private double dx = 2.5;
    private double dy = 1;
    private Rectangle rect;

    public Krava(double x, double y, Pane rootPane) {
        super(x,y,rootPane);
    }

    @Override
    public void createShape() {
        rect = new Rectangle(20,40);
        rect.setFill(Color.BLACK);
        this.shape = rect;
    }

    @Override
    public void move() {
        if(isPaused) return;
        x += dx;
        y += dy;

        double maxX = rootPane.getPrefWidth() - 20;
        double maxY = rootPane.getPrefHeight() - 20;

        if(x <= 0 || x >= maxX) {
            dx = -dx;
        }
        if(y <= 0 || y >= maxY) {
            dy = -dy;
        }
        updateVisualPosition();
    }
}

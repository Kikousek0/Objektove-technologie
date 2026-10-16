package com.example.spaceobject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Asteroid extends SpaceObject {
    private double dx = 2.0;
    private double dy = 2.0;
    private Rectangle rect;

    public Asteroid(double x, double y, Pane rootPane) {
        super(x, y, rootPane);
    }

    @Override
    protected void createShape() {
        rect = new Rectangle(25, 25);
        rect.setFill(Color.GRAY);
        this.shape = rect;
    }

    @Override
    public void move() {
        x += dx;
        y += dy;

        double maxX = rootPane.getPrefWidth() - 25;
        double maxY = rootPane.getPrefHeight() - 25;

        if (x <= 0 || x >= maxX) {
            dx = -dx;
        }
        if (y <= 50 || y >= maxY) {
            dy = -dy;
        }

        updateVisualPosition();
    }

    public void turnRed() {
        if (rect != null) {
            rect.setFill(Color.RED);
        }
    }
}
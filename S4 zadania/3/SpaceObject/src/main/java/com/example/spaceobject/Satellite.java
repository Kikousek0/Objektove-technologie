package com.example.spaceobject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Satellite extends SpaceObject {
    private double dy = 1.5;

    public Satellite(double x, double y, Pane rootPane) {
        super(x, y, rootPane);
    }

    @Override
    protected void createShape() {
        Polygon triangle = new Polygon(
                0.0, 15.0,
                10.0, 0.0,
                20.0, 15.0
        );
        triangle.setFill(Color.BLUE);
        this.shape = triangle;
    }

    @Override
    public void move() {
        y += dy;

        double maxY = rootPane.getPrefHeight() - 20;

        if (y <= 50 || y >= maxY) {
            dy = -dy;
        }

        updateVisualPosition();
    }

    public void shiftRight() {
        x += 20;
        if (x > rootPane.getPrefWidth() - 20) {
            x = rootPane.getPrefWidth() - 20;
        }
        updateVisualPosition();
    }
}
package com.example.spaceobject;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Planet extends SpaceObject {
    private double angle = 0.0;
    private final double radius = 40.0;
    private double centerX;
    private double centerY;

    public Planet(double x, double y, Pane rootPane) {
        super(x, y, rootPane);

        this.centerX = Math.max(radius + 10, Math.min(x, rootPane.getPrefWidth() - radius - 10));
        this.centerY = Math.max(50 + radius, Math.min(y, rootPane.getPrefHeight() - radius - 10));

        this.x = centerX + radius * Math.cos(angle);
        this.y = centerY + radius * Math.sin(angle);
        updateVisualPosition();
    }

    @Override
    protected void createShape() {
        Circle circle = new Circle(20);
        circle.setFill(Color.DARKGREEN);
        this.shape = circle;
    }

    @Override
    public void move() {

        angle += 0.03 * (speed / 2.0);

        x = centerX + radius * Math.cos(angle);
        y = centerY + radius * Math.sin(angle);

        updateVisualPosition();
    }
}

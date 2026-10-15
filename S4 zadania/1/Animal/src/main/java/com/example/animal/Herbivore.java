package com.example.animal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;

public class Herbivore extends Animal {
    private int direction = 0;
    private double distanceTraveled = 0;
    private final double sideLength = 80.0;

    public Herbivore(double x, double y, Pane rootPane) {
        super(x, y, rootPane);
    }

    @Override
    protected void createShape() {
        Circle circle = new Circle(10);
        circle.setFill(Color.GREEN);
        this.shape = circle;
    }

    @Override
    public void move() {
        if (isPaused) return;

        switch (direction) {
            case 0: x += speed; break;
            case 1: y += speed; break;
            case 2: x -= speed; break;
            case 3: y -= speed; break;
        }

        distanceTraveled += speed;
        if (distanceTraveled >= sideLength) {
            distanceTraveled = 0;
            direction = (direction + 1) % 4;
        }

        if (x < 10) x = 10;
        if (x > rootPane.getPrefWidth() - 20) x = rootPane.getPrefWidth() - 20;
        if (y < 50) y = 50;
        if (y > rootPane.getPrefHeight() - 20) y = rootPane.getPrefHeight() - 20;

        updateVisualPosition();
    }
}
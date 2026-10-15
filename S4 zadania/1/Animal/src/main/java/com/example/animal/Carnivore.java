package com.example.animal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;

public class Carnivore extends Animal {
    private double dx = 2.5;
    private double dy = 1.5;

    public Carnivore(double x, double y, Pane rootPane) {
        super(x, y, rootPane);
    }

    @Override
    protected void createShape() {
        Rectangle rect = new Rectangle(20, 20);
        rect.setFill(Color.RED);
        this.shape = rect;
    }

    @Override
    public void move() {
        if (isPaused) return;

        x += dx;
        y += dy;

        double maxX = rootPane.getPrefWidth() - 20;
        double maxY = rootPane.getPrefHeight() - 20;

        if (x <= 0 || x >= maxX) {
            dx = -dx;
        }
        if (y <= 50 || y >= maxY) {
            dy = -dy;
        }

        updateVisualPosition();
    }

    public void changeDirection() {
        dx = -dx;
        dy = -dy;
    }
}
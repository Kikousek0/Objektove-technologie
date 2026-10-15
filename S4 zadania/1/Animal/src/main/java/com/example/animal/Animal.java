package com.example.animal;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class Animal {
    protected double x, y;
    protected double speed = 2.0;
    protected boolean isPaused = false;
    protected Pane rootPane;
    protected Shape shape;

    public Animal(double x, double y, Pane rootPane) {
        this.x = x;
        this.y = y;
        this.rootPane = rootPane;
        createShape();
        updateVisualPosition();
    }

    protected abstract void createShape();

    public abstract void move();

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    protected void updateVisualPosition() {
        if (shape != null) {
            shape.setLayoutX(x);
            shape.setLayoutY(y);
        }
    }

    public void addToPane() {
        if (shape != null && !rootPane.getChildren().contains(shape)) {
            rootPane.getChildren().add(shape);
        }
    }
}
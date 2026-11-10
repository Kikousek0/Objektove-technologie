package com.example.hmyz;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class Hmyz {
    protected double x,y;
    protected Pane rootPane;
    protected Shape shape;
    protected double speed = 2;
    protected boolean markedForRemoval = false;

    public Hmyz(double x, double y, Pane rootPane) {
        this.x = x;
        this.y = y;
        this.rootPane = rootPane;
        createShape();
        updateVisualPosition();
    }

    protected abstract void createShape();

    public abstract void move();

    public boolean isMarkedForRemoval() {
        return markedForRemoval;
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

    public void up() {
        y -= 50;

        updateVisualPosition();
    }
}

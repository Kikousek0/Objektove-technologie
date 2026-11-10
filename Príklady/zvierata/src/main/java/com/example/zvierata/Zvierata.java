package com.example.zvierata;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class Zvierata {
    protected double x,y;
    protected Pane rootPane;
    protected Shape shape;
    protected boolean isPaused = false;

    public Zvierata(double x, double y, Pane rootPane) {
        this.x = x;
        this.y = y;
        this.rootPane = rootPane;
        createShape();
        updateVisualPosition();
    }

    public abstract void move();

    public abstract void createShape();

    public void updateVisualPosition() {
        if(shape != null) {
            shape.setLayoutY(y);
            shape.setLayoutX(x);
        }
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void addToPane() {
        if(shape != null && !rootPane.getChildren().contains(shape)) {
            rootPane.getChildren().add(shape);
        }
    }

}

package com.example.hmyz;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Mucha extends Hmyz{
    private double dx = 2.5;
    private double dy = 1.5;
    protected boolean isPaused = false;

    public Mucha(double x, double y, Pane rootPane) {
        super(x,y,rootPane);
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    @Override
    protected void createShape() {
        Circle circle = new Circle(10);
        circle.setFill(Color.RED);
        circle.setOnMousePressed(event -> {
            rootPane.getChildren().remove(circle);
            markedForRemoval = true;
            event.consume();
        });
        this.shape = circle;
    }

    @Override
    public void move() {
        if(isPaused) return;
        x += dx;
        y += dy;

        double maxX = rootPane.getPrefWidth() -20;
        double maxY = rootPane.getPrefHeight() -20;

        if(x <= 0 || x >= maxX) {
            dx = -dx;
        }
        if(y <=0 || y >= maxY) {
            dy = -dy;
        }

        updateVisualPosition();
    }

}

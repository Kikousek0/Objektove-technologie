package com.example.ball;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Ball extends Canvas {
    private GraphicsContext gc;
    private Timeline t;
    private boolean start = false;
    private double x = 40;
    private double y = 40;
    private double dx = 2.5;
    private double dy = 2.5;
    private double diameter = 40;

    public Ball() {
        super(600, 400);
        setLayoutX(0);
        setLayoutY(0);
        this.gc = getGraphicsContext2D();
        setOnMousePressed((e -> startstop()));
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F) {
                t.setRate(t.getRate() * 1.25);
            } else if (e.getCode() == KeyCode.S) {
                t.setRate(Math.max(0.1, t.getRate() * 0.75));
            }
        });
        setFocusTraversable(true);
        t = new Timeline(new KeyFrame(Duration.millis(16), e -> pohyb()));
        t.setCycleCount(Timeline.INDEFINITE);
        vykresli(Color.RED);
    }

    public void startstop() {
        if (start) {
            t.stop();
            start = false;
        } else {
            t.play();
            start = true;
            requestFocus();
        }
    }
    public void vykresli(Color red) {
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.setFill(Color.RED);
        gc.fillOval(x,y,diameter,diameter);
    }
    public void pohyb() {
        x += dx;
        y += dy;
        if (x <= 0 || x + diameter >= getWidth()) {
            dx = -dx;
            x = Math.max(0, Math.min(x, getWidth() - diameter));
        }
        if (y <= 0 || y + diameter >= getHeight()) {
            dy = -dy;
            y = Math.max(0, Math.min(y, getHeight() - diameter));
        }
        vykresli(Color.RED);
    }

}

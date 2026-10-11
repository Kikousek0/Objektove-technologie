package com.example.auto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Auto extends Canvas {
    private GraphicsContext gc;
    private Timeline t;
    private boolean start = false;
    private boolean automaticMode = true;

    private double x = 50;
    private double y = 50;
    private double width = 60;
    private double height = 30;

    private double dx = 3.0;
    private double dy = 0;
    private double manualSpeed = 4.0;

    public Auto() {
        super(500, 400);
        this.gc = getGraphicsContext2D();

        setOnMousePressed(e -> startstop());

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M) {
                automaticMode = !automaticMode;
                if (automaticMode) {
                    dx = 3.0;
                    dy = 0;
                } else {
                    dx = 0;
                    dy = 0;
                }
            }

            if (!automaticMode) {
                if (e.getCode() == KeyCode.UP) {
                    dy = -manualSpeed;
                    dx = 0;
                } else if (e.getCode() == KeyCode.DOWN) {
                    dy = manualSpeed;
                    dx = 0;
                } else if (e.getCode() == KeyCode.LEFT) {
                    dx = -manualSpeed;
                    dy = 0;
                } else if (e.getCode() == KeyCode.RIGHT) {
                    dx = manualSpeed;
                    dy = 0;
                }
            }
        });

        setFocusTraversable(true);

        t = new Timeline(new KeyFrame(Duration.millis(16), e -> pohyb()));
        t.setCycleCount(Timeline.INDEFINITE);

        vykresli();
        requestFocus();
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

    public void vykresli() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, getWidth(), getHeight());

        if (automaticMode) {
            gc.setFill(Color.BLUE);
        } else {
            gc.setFill(Color.GREEN);
        }
        gc.fillRect(x, y, width, height);
    }

    public void pohyb() {
        if (automaticMode) {
            x += dx;
            if (x <= 0 || x + width >= getWidth()) {
                dx = -dx;
                x = Math.max(0, Math.min(x, getWidth() - width));
            }
        } else {
            x += dx;
            y += dy;

            if (x < 0) x = 0;
            if (x + width > getWidth()) x = getWidth() - width;
            if (y < 0) y = 0;
            if (y + height > getHeight()) y = getHeight() - height;
        }

        vykresli();
    }
}

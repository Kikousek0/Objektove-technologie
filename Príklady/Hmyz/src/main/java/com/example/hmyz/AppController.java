package com.example.hmyz;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppController {
    private Random random = new Random();
    private List<Hmyz> hmyz = new ArrayList<>();
    private boolean stop = false;
    @FXML private Pane root;
    private AnimationTimer timer;

    @FXML
    public void initialize() {
        root.setFocusTraversable(true);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                hmyz.removeIf(h -> {
                    if(h.isMarkedForRemoval()) {
                        return true;
                    }
                    h.move();
                    return false;
                });
            }
        };
        timer.start();
    }

    @FXML private void mucha() {
        double randomX = 30 + random.nextDouble() * (root.getPrefWidth() - 60);
        double randomY = 60 + random.nextDouble() * (root.getPrefHeight() - 90);

        Mucha mucha = new Mucha(randomX, randomY, root);
        mucha.addToPane();
        hmyz.add(mucha);
    }

    @FXML
    private void chrobak() {
        double height = root.getPrefHeight() > 0 ? root.getPrefHeight() : 50;

        double randomX = 30 + random.nextDouble() * (root.getPrefWidth() - 60);
        double startY = height * 0.6;
        double endY = height - 50;
        double randomY = startY + random.nextDouble() * (endY - startY);

        Chrobak chrobak = new Chrobak(randomX, randomY, root);
        chrobak.addToPane();
        hmyz.add(chrobak);
    }

    @FXML
    private void keyPressed(KeyEvent event)  {
        if(event.getCode() == KeyCode.S) {
            stop = !stop;
            for(Hmyz h : hmyz) {
                if(h instanceof Mucha) {
                    ((Mucha) h).setPaused(stop);
                }
            }
        }
        if(event.getCode() == KeyCode.W) {
            for(Hmyz h : hmyz) {
                h.up();
                h.updateVisualPosition();
            }
        }
    }
}

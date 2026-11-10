package com.example.zvierata;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppController {
    private AnimationTimer timer;
    private Random random = new Random();
    private List<Zvierata> zvery = new ArrayList<>();
    private boolean stop = false;
    @FXML
    private Pane root;
    @FXML
    public void initialize() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Zvierata z : zvery) {
                    z.move();
                }
            }
        };
        timer.start();
    }
    @FXML
    private void krava() {
        double randomX = 30 + random.nextDouble() * (root.getPrefWidth() - 50);
        double randomY = 50 + random.nextDouble() * (root.getPrefHeight() - 80);

        Krava krava = new Krava(randomX, randomY, root);
        krava.addToPane();
        zvery.add(krava);
    }
    @FXML
    private void ryba() {
        double randomX = 30 + random.nextDouble() * (root.getPrefWidth() - 50);
        double randomY = 50 + random.nextDouble() * (root.getPrefHeight() - 80);

        Ryba ryba = new Ryba(randomX, randomY, root);
        ryba.addToPane();
        zvery.add(ryba);
    }
    @FXML
    private void stop() {
        stop = !stop;
        for(Zvierata z : zvery) {
            z.setPaused(stop);
        }
    }
    @FXML
    public void keyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.C) {
            for(Zvierata z : zvery) {
                if(z instanceof Ryba) {
                    ((Ryba) z).changeColor();
                }
            }
        }
    }
}

package com.example.spaceobject;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {
    private Random random = new Random();
    private List<SpaceObject> SpaceObjects = new ArrayList<>();
    @FXML private Pane root;
    private AnimationTimer timer;

    @FXML
    public void initialize() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (SpaceObject SpaceObject: SpaceObjects) {
                    SpaceObject.move();
                }
            }
        };
        timer.start();
    }

    @FXML private void planet(){
        double randomX = 30 + random.nextDouble() * (root.getPrefWidth() - 60);
        double randomY = 60 + random.nextDouble() * (root.getPrefHeight() - 90);

        Planet planet = new Planet(randomX,randomY, root);
        planet.addToPane();
        SpaceObjects.add(planet);
    }

    @FXML private void asteroid(){}

    @FXML private void satelit(){}

    @FXML private void changeColor(){}

    @FXML private void posun(){}
}

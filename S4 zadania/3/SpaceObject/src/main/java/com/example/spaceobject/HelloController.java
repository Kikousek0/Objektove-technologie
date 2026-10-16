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

    @FXML
    private void asteroid() {
        double width = root.getPrefWidth() > 0 ? root.getPrefWidth() : 600;
        double height = root.getPrefHeight() > 0 ? root.getPrefHeight() : 400;

        double randomX = 30 + random.nextDouble() * (width - 60);
        double randomY = 60 + random.nextDouble() * (height - 90);

        Asteroid asteroid = new Asteroid(randomX, randomY, root);
        asteroid.addToPane();
        SpaceObjects.add(asteroid);
    }

    @FXML
    private void changeColor() {
        for (SpaceObject obj : SpaceObjects) {
            if (obj instanceof Asteroid) {
                ((Asteroid) obj).turnRed();
            }
        }
    }

    @FXML
    private void satelit() {
        double width = root.getPrefWidth() > 0 ? root.getPrefWidth() : 600;
        double height = root.getPrefHeight() > 0 ? root.getPrefHeight() : 400;

        double randomX = 30 + random.nextDouble() * (width - 60);
        double randomY = 60 + random.nextDouble() * (height - 90);

        Satellite satellite = new Satellite(randomX, randomY, root);
        satellite.addToPane();
        SpaceObjects.add(satellite);
    }
    @FXML
    private void posun() {
        for (SpaceObject obj : SpaceObjects) {
            if (obj instanceof Satellite) {
                ((Satellite) obj).shiftRight();
            }
        }
    }

}

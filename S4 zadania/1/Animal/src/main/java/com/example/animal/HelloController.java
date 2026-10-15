package com.example.animal;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private Pane root;

    private List<Animal> animals = new ArrayList<>();
    private boolean globalPause = false;
    private Random random = new Random();
    private AnimationTimer timer;

    @FXML
    public void initialize() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Animal animal : animals) {
                    animal.move();
                }
            }
        };
        timer.start();
    }

    @FXML
    private void Herbivore() {
        double randomX = 30 + random.nextDouble() * (root.getPrefWidth() - 60);
        double randomY = 60 + random.nextDouble() * (root.getPrefHeight() - 90);

        Herbivore herbivore = new Herbivore(randomX, randomY, root);
        herbivore.setPaused(globalPause);
        herbivore.addToPane();
        animals.add(herbivore);
    }

    @FXML
    private void Carnivore() {
        double randomX = 30 + random.nextDouble() * (root.getPrefWidth() - 60);
        double randomY = 60 + random.nextDouble() * (root.getPrefHeight() - 90);

        Carnivore carnivore = new Carnivore(randomX, randomY, root);
        carnivore.setPaused(globalPause);
        carnivore.addToPane();
        animals.add(carnivore);
    }

    @FXML
    private void ZmenaSmeru() {
        for (Animal animal : animals) {
            if (animal instanceof Carnivore) {
                ((Carnivore) animal).changeDirection();
            }
        }
    }

    @FXML
    private void Stop() {
        globalPause = !globalPause;
        for (Animal animal : animals) {
            animal.setPaused(globalPause);
        }
    }
}

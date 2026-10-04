package com.example.kalkulackahmotnosti;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private Label tona, kg, g, mg;
    @FXML private TextField pole;

    @FXML protected void preved() {
        try {
            Double cislo = Double.parseDouble(pole.getText());
            tona.setText(String.format("%.6f t", cislo / 1_000_000));
            kg.setText(String.format("%.4f kg", cislo / 1_000));
            g.setText(String.format("%.2f g", cislo));
            mg.setText(String.format("%.2f mg", cislo * 1_000));
        }
        catch (NumberFormatException e) {
            g.setText("Zadaj platné číslo");
            tona.setText("-");
            kg.setText("-");
            mg.setText("-");
        }
    }
}

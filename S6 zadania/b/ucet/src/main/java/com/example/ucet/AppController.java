package com.example.ucet;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

public class AppController {
    @FXML
    private TextField starting_balance;
    @FXML
    private Label balance;
    @FXML
    private ComboBox<String> typ;
    @FXML
    private TextField suma;
    @FXML
    private TextArea text;

    private Ucet ucet;

    @FXML
    public void initialize() {
        typ.getItems().addAll("Vklad", "Výber");
        typ.setValue("Vklad");
    }

    @FXML
    private void vytvor_ucet() {
        try {
            double pociatocnaSuma = Double.parseDouble(starting_balance.getText());
            ucet = new Ucet(pociatocnaSuma);
            balance.setText(ucet.getZostatok() + " €");
        } catch (NumberFormatException e) {
            text.appendText("Zadajte platnú počiatočnú sumu!\n");
        }
    }
    @FXML
    private void run() {
        if (skontrolujUcet()) return;

        try {
            String typOperacie = typ.getValue();
            double s = Double.parseDouble(suma.getText());

            Zamestnanec zamestnanec = new Zamestnanec(ucet, typOperacie, s, text);
            new Thread(zamestnanec).start();

            aktualizujZostatokAsync();
        } catch (NumberFormatException e) {
            text.appendText("Zadajte platnú sumu!\n");
        }
    }
    @FXML
    private void run5() {
        if(skontrolujUcet()) return;

        Random random = new Random();
        String[] operacie = {"Vklad", "Výber"};

        text.appendText("Spúšťa sa 5 zamestnancov zároveň.\n");

        for(int i = 0; i < 5; i++) {
            String nahodnyTyp = operacie[random.nextInt(operacie.length)];
            double nahodnaSuma = 10 + random.nextInt(70);

            Zamestnanec zamestnanec = new Zamestnanec(ucet, nahodnyTyp, nahodnaSuma, text);
            new Thread(zamestnanec).start();
        }
        aktualizujZostatokAsync();
    }
    private boolean skontrolujUcet() {
        if(ucet == null) {
            text.appendText("Najskôr musíte vytvoriť účet!\n");
            return true;
        }
        return false;
    }

    private void aktualizujZostatokAsync() {
        new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (ucet != null) {
                javafx.application.Platform.runLater(()->
                        balance.setText(ucet.getZostatok() + " €")
                );
            }
        }).start();
    }
}

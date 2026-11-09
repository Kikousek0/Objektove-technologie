package com.example.ucet;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Zamestnanec implements Runnable{
    private static int idCounter = 1;
    private final int id;
    private final Ucet ucet;
    private final String typOperacie;
    private final double suma;
    private final TextArea logArea;

    public Zamestnanec(Ucet ucet, String typOperacie, double suma, TextArea logArea) {
        this.id = idCounter++;
        this.ucet = ucet;
        this.typOperacie = typOperacie;
        this.suma = suma;
        this.logArea = logArea;
    }
    @Override
    public void run() {
        boolean success = false;
        if("Vklad".equals(typOperacie)) {
            ucet.vklad(suma);
            success = true;
        } else if("Výber".equals(typOperacie)) {
            success = ucet.vyber(suma);
        }
        final boolean finalSuccess = success;
        String vysledokText = finalSuccess ? "úspešný" : "neúspešný (nedostatok peňazí)";
        String sprava = "Zamestnanec #" + id + ": " + typOperacie + " " + suma + " € "
                + vysledokText + ". Aktuálny zostatok: " + ucet.getZostatok() + " €\n";

        Platform.runLater(() -> logArea.appendText(sprava));
    }
}

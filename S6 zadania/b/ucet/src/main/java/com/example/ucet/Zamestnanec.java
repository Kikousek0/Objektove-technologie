package com.example.ucet;

public class Zamestnanec {
    private static int idCounter = 1;
    private final int id;
    private final Ucet ucet;
    private final String typOperacie;
    private final double suma;
    private final TextAre logArea;

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
            ucet.vyber(suma);
            success = true;
        }
        final boolean finalSuccess = success;
        String sprava = String.format("Zamestnanec #%d: %s %.2f € %s. Aktuálny zostatok: %.2f €\n",
                id, typOperacie, suma, finalSuccess ? "úspešný" : "neúspešný (nedostatok peňazí)", ucet.getZostatok());

        Platform.runLater(() -> logArea.appendText(sprava));
    }
}

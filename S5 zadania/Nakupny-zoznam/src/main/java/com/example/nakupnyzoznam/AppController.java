package com.example.nakupnyzoznam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppController {
    @FXML private TextField text1, text2;
    @FXML private ListView<NakupnaPolozka> list;
    @FXML private Label vysledok;

    private ObservableList<NakupnaPolozka> zoznam = FXCollections.observableArrayList();

    @FXML private void initialize() {
        list.setItems(zoznam);
        aktualizujPocet();
    }

    @FXML private void add() {
        String nazov = text1.getText().trim();
        String pocetstr = text2.getText().trim();

        if(nazov.isEmpty() || pocetstr.isEmpty()) {
            return;
        }
        try {
            int pocet = Integer.parseInt(pocetstr);

            NakupnaPolozka novaPolozka = new NakupnaPolozka(nazov, pocet);
            zoznam.add(novaPolozka);

            text1.clear();
            text2.clear();

            aktualizujPocet();
        }
        catch (NumberFormatException e) {
            text2.setText("Zadaj číslo");
        }
    }

    @FXML private void delete() {
        NakupnaPolozka vybrana = list.getSelectionModel().getSelectedItem();
        if(vybrana != null) {
            zoznam.remove(vybrana);
            aktualizujPocet();
        }
        list.getSelectionModel().clearSelection();
    }
    private void aktualizujPocet() {
        vysledok.setText("Celkový počet položiek: " + zoznam.size());
    }
    @FXML private void edit() {
        NakupnaPolozka vybrana = list.getSelectionModel().getSelectedItem();
        if(vybrana != null) {
            String novyNazov = text1.getText().trim();
            String novyPocetstr = text2.getText().trim();
            if (novyNazov.isEmpty()) {
                novyNazov = vybrana.getNazov();
            }
            int novyPocet;
            try {
                if(novyPocetstr.isEmpty()) {
                    novyPocet = vybrana.getPocet();
                } else {
                    novyPocet = Integer.parseInt(novyPocetstr);
                }
                vybrana.setNazov(novyNazov);
                vybrana.setPocet(novyPocet);

                list.refresh();

                text1.clear();
                text2.clear();
            } catch (NumberFormatException e) {
                text2.setText("Zadaj číslo");
            }
            list.getSelectionModel().clearSelection();
        }
    }

}

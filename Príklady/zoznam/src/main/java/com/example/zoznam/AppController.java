package com.example.zoznam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppController {
    @FXML
    private TextField nazov, kusy;
    @FXML
    private Label spolu;
    @FXML
    private ListView<polozka> view;

    private ObservableList<polozka> zoznam = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        view.setItems(zoznam);
        aktualizujPocet();
    }

    @FXML
    private void add() {
        String text = nazov.getText().trim();
        String pocetstr = kusy.getText().trim();
        if (text.isEmpty() || pocetstr.isEmpty()) {
            return;
        }
        try {
            int pocet = Integer.parseInt(pocetstr);

            polozka novapolozka = new polozka(text, pocet);
            zoznam.add(novapolozka);

            vycisti();

            aktualizujPocet();
        } catch (NumberFormatException e) {
            kusy.setText("Zadaj platné číslo!");
        }
    }

    @FXML
    private void del() {
        polozka vybrana = view.getSelectionModel().getSelectedItem();
        if (vybrana != null) {
            zoznam.remove(vybrana);
            aktualizujPocet();
        }
        view.getSelectionModel().clearSelection();
    }

    private void aktualizujPocet() {
        spolu.setText("Celkový počet položiek: " + zoznam.size());
    }

    @FXML
    private void edit() {
        polozka vybrana = view.getSelectionModel().getSelectedItem();
        if (vybrana != null) {
            String novyNazov = nazov.getText().trim();
            String novypocetstr = kusy.getText().trim();
            if (novyNazov.isEmpty()) {
                novyNazov = vybrana.getNazov();
            }
            int novypocet;
            try {
                if (!novypocetstr.isEmpty()) {
                    novypocet = Integer.parseInt(novypocetstr);
                } else {
                    novypocet = vybrana.getPocet();
                }
                vybrana.setNazov(novyNazov);
                vybrana.setPocet(novypocet);

                view.refresh();

                vycisti();
            } catch (NumberFormatException e) {
                kusy.setText("Zadaj platné číslo!");
            }
            view.getSelectionModel().clearSelection();
        }
    }
    public void vycisti() {
        nazov.clear();
        kusy.clear();
    }
}

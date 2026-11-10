package com.example.evidencia;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {

    // Modelová trieda pre hardvér
    public static class HardwareItem {
        private String nazov;
        private String typ;
        private String serioveCislo;
        private double kupnaCena;
        private String koniecZaruky;

        public HardwareItem(String nazov, String typ, String serioveCislo, double kupnaCena, String koniecZaruky) {
            this.nazov = nazov;
            this.typ = typ;
            this.serioveCislo = serioveCislo;
            this.kupnaCena = kupnaCena;
            this.koniecZaruky = koniecZaruky;
        }

        public String getNazov() { return nazov; }
        public void setNazov(String nazov) { this.nazov = nazov; }

        public String getTyp() { return typ; }
        public void setTyp(String typ) { this.typ = typ; }

        public String getSerioveCislo() { return serioveCislo; }
        public void setSerioveCislo(String serioveCislo) { this.serioveCislo = serioveCislo; }

        public double getKupnaCena() { return kupnaCena; }
        public void setKupnaCena(double kupnaCena) { this.kupnaCena = kupnaCena; }

        public String getKoniecZaruky() { return koniecZaruky; }
        public void setKoniecZaruky(String koniecZaruky) { this.koniecZaruky = koniecZaruky; }
    }

    @FXML private TextField nazov;
    @FXML private TextField typ;
    @FXML private TextField seriove_cislo;
    @FXML private TextField cena;
    @FXML private TextField koniec_zaruky;
    @FXML private ListView<HardwareItem> view;
    @FXML private CheckBox chkZaruka;

    private ObservableList<HardwareItem> hardwareList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Prepojenie zoznamu s ListView
        view.setItems(hardwareList);

        // Definícia toho, ako sa položka v ListView vykreslí (CellFactory)
        view.setCellFactory(param -> new ListCell<HardwareItem>() {
            @Override
            protected void updateItem(HardwareItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Ak je checkbox zaškrtnutý -> vráti dátum konca záruky, inak -> sériové číslo - názov
                    if (chkZaruka.isSelected()) {
                        setText("Záruka do: " + item.getKoniecZaruky());
                    } else {
                        setText(item.getSerioveCislo() + " - " + item.getNazov());
                    }
                }
            }
        });

        // Vlákno pre obsluhu Checkboxu (splnenie požiadavky na použitie vlákna)
        chkZaruka.setOnAction(e -> {
            new Thread(() -> {
                try {
                    // Simulácia spracovania na pozadí (napr. načítanie/zmena stavu)
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // Bezpečná aktualizácia UI cez JavaFX Application Thread
                Platform.runLater(() -> {
                    view.refresh(); // Vynúti preukreslenie buniek v ListView
                });
            }).start();
        });

        // Kliknutie na položku v zozname (naplní formulár pre úpravu)
        view.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                nazov.setText(newVal.getNazov());
                typ.setText(newVal.getTyp());
                seriove_cislo.setText(newVal.getSerioveCislo());
                cena.setText(String.valueOf(newVal.getKupnaCena()));
                koniec_zaruky.setText(newVal.getKoniecZaruky());
            }
        });
    }

    @FXML
    private void add() {
        try {
            HardwareItem item = new HardwareItem(
                    nazov.getText(),
                    typ.getText(),
                    seriove_cislo.getText(),
                    Double.parseDouble(cena.getText()),
                    koniec_zaruky.getText()
            );
            hardwareList.add(item);
            clear();
        } catch (Exception e) {
            // Ošetrenie chybnej ceny
        }
    }

    @FXML
    private void edit() {
        HardwareItem selected = view.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setNazov(nazov.getText());
                selected.setTyp(typ.getText());
                selected.setSerioveCislo(seriove_cislo.getText());
                selected.setKupnaCena(Double.parseDouble(cena.getText()));
                selected.setKoniecZaruky(koniec_zaruky.getText());

                view.refresh();
                clear();
            } catch (Exception e) {
                // Ošetrenie chybnej ceny
            }
        }
    }

    @FXML
    private void del() {
        HardwareItem selected = view.getSelectionModel().getSelectedItem();
        if (selected != null) {
            hardwareList.remove(selected);
            clear();
        }
    }

    @FXML
    private void clear() {
        nazov.clear();
        typ.clear();
        seriove_cislo.clear();
        cena.clear();
        koniec_zaruky.clear();
        view.getSelectionModel().clearSelection();
    }
}
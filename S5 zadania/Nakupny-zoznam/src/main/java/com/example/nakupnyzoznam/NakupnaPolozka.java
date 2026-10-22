package com.example.nakupnyzoznam;

public class NakupnaPolozka {
    private String nazov;
    private int pocet;

    public NakupnaPolozka(String nazov, int pocet) {
        this.nazov = nazov;
        this.pocet = pocet;
    }

    public String getNazov() {
        return nazov;
    }

    public int getPocet() {
        return pocet;
    }
    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }
    @Override
    public String toString() {
        return nazov + " (" + pocet + " ks)";
    }
}

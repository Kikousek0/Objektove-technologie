package com.example.nakupnyzoznam;

public class NakupnaPolozka {
    private String nazov;
    private int pocet;

    public NakupnaPolozka(String nazov, int pocet) {
        this.nazov = nazov;
        this.pocet = pocet;
    }

    public String getName() {
        return nazov;
    }

    public int getPocet() {
        return pocet;
    }

    @Override
    public String toString() {
        return nazov + " (" + pocet + " ks)";
    }
}

package com.example.ucet;

public class Ucet {
    private double zostatok;

    public Ucet(double pociatocnyZostatok) {
        this.zostatok = pociatocnyZostatok;
    }

    public synchronized void vklad(double suma) {
        zostatok += suma;
    }

    public synchronized boolean vyber(double suma) {
        if (zostatok >= suma) {
            zostatok -= suma;
            return true;
        }
        return false;
    }

    public synchronized double getZostatok() {
        return zostatok;
    }
}

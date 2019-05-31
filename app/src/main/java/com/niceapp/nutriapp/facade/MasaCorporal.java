package com.niceapp.nutriapp.facade;

public class MasaCorporal {

    public static double getMasaCorporal(double estaturaE, int pesoE) {
        double masa = pesoE / (estaturaE * estaturaE);
        return masa;
    }

    public String masaCorporal() {
        return null;
    }
}

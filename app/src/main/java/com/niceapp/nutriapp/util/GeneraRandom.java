package com.niceapp.nutriapp.util;

import java.util.Random;

public class GeneraRandom {


    public static String getRandomComidas() {
        Random r = new Random();
        return String.valueOf(r.nextInt(3) + 1);
    }

    public static String getRandomConsejos() {
        Random r = new Random();
        return String.valueOf(r.nextInt(7) + 1);
    }
}

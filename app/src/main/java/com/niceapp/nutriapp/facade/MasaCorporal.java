package com.niceapp.nutriapp.facade;

import com.niceapp.nutriapp.util.Variables;

public class MasaCorporal {

    public static double getMasaCorporal(double estaturaE, int pesoE) {
        double masa = pesoE / (estaturaE * estaturaE);
        return masa;
    }

    public static boolean cumpleReglas(double estaturaE, int edadE, int pesoE) {
        if (estaturaE < 0 || estaturaE > 3 || edadE < 0 || edadE > 100 || pesoE < 20 || pesoE > 300) {
            return false;
        }
        return true;
    }


    public static String getTipoPaciente(int edadE) {
        if (edadE <= 14) {
            return Variables.TipoPaciente.NIÑO;
        } else if (edadE <= 60) {
            return Variables.TipoPaciente.ADULTO;
        } else {
            return Variables.TipoPaciente.ANCIANO;
        }
    }

    public static String getEstadoPaciente(double masaCorporalE) {
        if (masaCorporalE < 18.5) {
            return Variables.EstadoPaciente.DESNUTRICION;
        } else if (masaCorporalE < 24.9) {
            return Variables.EstadoPaciente.NORMAL;
        } else {
            return Variables.EstadoPaciente.SOBREPESO;
        }
    }
}

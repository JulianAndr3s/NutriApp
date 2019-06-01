package com.niceapp.nutriapp.facade;

import com.niceapp.nutriapp.util.Variables;

import org.junit.Test;

import static org.junit.Assert.*;

public class MasaCorporalTest {

    @Test
    public void cumpleReglas() {
        /**
         * Datos erroneos en la estatura
         */
        assertFalse(MasaCorporal.cumpleReglas(7,62,42));

        /**
         * datos erroneos en la edad
         */
        assertFalse(MasaCorporal.cumpleReglas(2,870,51));

        /**
         * Datos erroneos en el peso
         */
        assertFalse(MasaCorporal.cumpleReglas(2,31,920));

        /**
         * todos los datos erroneos
         */
        assert(MasaCorporal.cumpleReglas(1,21,73));

        /**
         * Datos correctos
         */
    }

    @Test
    public void getTipoPaciente() {
        assertEquals(Variables.TipoPaciente.NIÑO,MasaCorporal.getTipoPaciente(11));
        assertEquals(Variables.TipoPaciente.ADULTO,MasaCorporal.getTipoPaciente(25));
        assertEquals(Variables.TipoPaciente.ANCIANO,MasaCorporal.getTipoPaciente(71));
    }

    @Test
    public void getEstadoPaciente() {
        assertEquals(Variables.EstadoPaciente.DESNUTRICION,MasaCorporal.getEstadoPaciente(9));
        assertEquals(Variables.EstadoPaciente.NORMAL,MasaCorporal.getEstadoPaciente(19));
        assertEquals(Variables.EstadoPaciente.SOBREPESO,MasaCorporal.getEstadoPaciente(81));
    }
}
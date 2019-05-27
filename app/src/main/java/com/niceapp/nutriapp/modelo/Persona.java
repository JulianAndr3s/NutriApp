package com.niceapp.nutriapp.modelo;

import com.google.firebase.database.IgnoreExtraProperties;

public class Persona {

    private String usuario;
    private int edad;
    private double peso;
    private int estatura;

    public Persona() {
        super();
    }

    public Persona(String usuario, int edad, double peso, int estatura) {
        this.usuario = usuario;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }
}

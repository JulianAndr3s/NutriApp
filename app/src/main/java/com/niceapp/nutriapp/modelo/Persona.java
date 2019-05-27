package com.niceapp.nutriapp.modelo;

public class Persona {

    private String usuario;
    private int edad;
    private double peso;
    private int altura;

    public Persona() {
    }

    public Persona(String usuario, int edad, double peso, int altura) {
        this.usuario = usuario;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}

package com.niceapp.nutriapp.modelo;

public class Persona {

    private String usuario;
    private int edad;
    private int peso;
    private double estatura;
    private double masaCorporal;
    private String estadoNutricion;
    private String tipoPaciente;



    public String getEstadoNutricion() {
        return estadoNutricion;
    }

    public void setEstadoNutricion(String estadoNutricion) {
        this.estadoNutricion = estadoNutricion;
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }


    public Persona(String usuario, int edad, int peso, double estatura, double masaCorporal, String estadoNutricion, String tipoPaciente) {
        this.usuario = usuario;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
        this.masaCorporal = masaCorporal;
        this.estadoNutricion = estadoNutricion;
        this.tipoPaciente = tipoPaciente;
    }

    public Persona(int masaCorporal) {
        super();
        this.masaCorporal = masaCorporal;
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

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public double getMasaCorporal() {
        return masaCorporal;
    }

    public void setMasaCorporal(double masaCorporal) {
        this.masaCorporal = masaCorporal;
    }
}

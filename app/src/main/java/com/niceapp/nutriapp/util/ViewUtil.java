package com.niceapp.nutriapp.util;

import android.support.v7.app.AppCompatActivity;

public class ViewUtil {

    private AppCompatActivity appCompatActivity;

    public ViewUtil( AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    private void mostrarFlecha() {
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setToolBar(String titulo, String subtitulo) {
        mostrarFlecha();
        appCompatActivity.getSupportActionBar().setTitle(titulo);
        appCompatActivity.getSupportActionBar().setSubtitle(subtitulo);
    }

    public void setToolBar(String titulo) {
        mostrarFlecha();
        appCompatActivity.getSupportActionBar().setTitle(titulo);
    }

}

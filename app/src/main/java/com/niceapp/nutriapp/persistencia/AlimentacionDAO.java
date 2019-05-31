package com.niceapp.nutriapp.persistencia;

import com.google.firebase.database.DatabaseReference;
import com.niceapp.nutriapp.util.GeneraRandom;

public class AlimentacionDAO {

    DatabaseReference dbRef;

    public AlimentacionDAO(DatabaseReference dbRef) {
        super();
        this.dbRef = dbRef;
    }

    public DatabaseReference getDesayuno() {
        return dbRef.child("Desayuno").child(GeneraRandom.getRandomComidas());
    }

    public DatabaseReference getAlmuerzo() {
        return dbRef.child("Almuerzo").child(GeneraRandom.getRandomComidas());
    }

    public DatabaseReference getComida() {
        return dbRef.child("Comida").child(GeneraRandom.getRandomComidas());
    }
}


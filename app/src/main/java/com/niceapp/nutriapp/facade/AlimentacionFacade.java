package com.niceapp.nutriapp.facade;

import com.google.firebase.database.DatabaseReference;
import com.niceapp.nutriapp.persistencia.AlimentacionDAO;

public class AlimentacionFacade {

    AlimentacionDAO alimentacionDAO;

    public AlimentacionFacade(DatabaseReference dbRef) {
        super();
        alimentacionDAO = new AlimentacionDAO(dbRef);
    }


    public DatabaseReference getDesayuno() {
        return alimentacionDAO.getDesayuno();
    }

    public DatabaseReference getAlmuerzo() {
        return alimentacionDAO.getAlmuerzo();
    }

    public DatabaseReference getComida() {
        return alimentacionDAO.getComida();
    }
}

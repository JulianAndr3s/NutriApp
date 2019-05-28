package com.niceapp.nutriapp.datosusuario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.niceapp.nutriapp.R;
import com.niceapp.nutriapp.util.ViewUtil;

public class IngresoDatosActivity extends AppCompatActivity {

    private ViewUtil viewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        viewUtil = new ViewUtil(this);
        viewUtil.setToolBar("Ingresa tus datos");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
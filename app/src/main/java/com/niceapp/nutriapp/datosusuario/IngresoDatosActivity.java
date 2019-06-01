package com.niceapp.nutriapp.datosusuario;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niceapp.nutriapp.R;
import com.niceapp.nutriapp.util.ViewUtil;

public class IngresoDatosActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference dbRef;
    FirebaseAuth mAuth;

    private TextView edadTxt, pesoTxt, alturaTxt, imcTxt, estadoNutricionTxt, tipoPacienteTxt;
    private ViewUtil viewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        initComponents();
    }

    private void initComponents() {
        viewUtil = new ViewUtil(this);
        viewUtil.setToolBar("Ingresa tus datos");
        edadTxt = findViewById(R.id.edadTxt);
        pesoTxt = findViewById(R.id.pesoTxt);
        alturaTxt = findViewById(R.id.alturaTxt);
        imcTxt = findViewById(R.id.imcTxt);
        estadoNutricionTxt = findViewById(R.id.estadoNutricionTxt);
        tipoPacienteTxt = findViewById(R.id.tipoPacienteTxt);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        dbRef = database.getReference().child("persona").child(userId);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    edadTxt.setText(dataSnapshot.child("edad").getValue().toString());
                    pesoTxt.setText(dataSnapshot.child("peso").getValue().toString());
                    alturaTxt.setText(dataSnapshot.child("estatura").getValue().toString());
                    imcTxt.setText(dataSnapshot.child("masaCorporal").getValue().toString());
                    estadoNutricionTxt.setText(dataSnapshot.child("estadoNutricion").getValue().toString());
                    tipoPacienteTxt.setText(dataSnapshot.child("tipoPaciente").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
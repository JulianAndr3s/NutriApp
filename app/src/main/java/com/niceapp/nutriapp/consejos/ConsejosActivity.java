package com.niceapp.nutriapp.consejos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niceapp.nutriapp.R;
import com.niceapp.nutriapp.util.ViewUtil;

public class ConsejosActivity extends AppCompatActivity {

    private ViewUtil viewUtil;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);
        viewUtil = new ViewUtil(this);
        viewUtil.setToolBar("Consejos");
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getDatos(View view) {
        databaseReference.child("Recetas").child("Desayuno").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists() || dataSnapshot == null) {
                    System.out.println("Nada");
                } else {
                    String desayuno = dataSnapshot.getValue().toString();
                    Toast.makeText(getApplicationContext(),"Si lo cogimos!", Toast.LENGTH_LONG);
                    System.out.println(desayuno);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
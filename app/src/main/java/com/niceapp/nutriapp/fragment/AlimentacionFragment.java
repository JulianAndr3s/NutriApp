package com.niceapp.nutriapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niceapp.nutriapp.R;


public class AlimentacionFragment extends Fragment {


    private Button getDatosBtn;
    private TextView nombreTxt, edadTxt, estaturaTxt, pesoTxt;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_alimentacion, container, false);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        initComponents(view);
        getDatosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("persona").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String usuario = dataSnapshot.child("usuario").getValue().toString();
                            String edad = dataSnapshot.child("edad").getValue().toString();
                            String peso = dataSnapshot.child("peso").getValue().toString();
                            String estatura = dataSnapshot.child("estatura").getValue().toString();

                            nombreTxt.setText("Usuario "+ usuario);
                            edadTxt.setText("Edad "+edad);
                            estaturaTxt.setText("Estatura "+estatura);
                            pesoTxt.setText("Peso "+peso);
                        }else{
                            System.out.println("Nothing");
                            Toast.makeText(getContext(),"Nothing else metters xD",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }

    private void initComponents(View view) {
        nombreTxt=view.findViewById(R.id.nombreTxt);
        edadTxt=view.findViewById(R.id.edadTxt);
        estaturaTxt=view.findViewById(R.id.estaturaTxt);
        pesoTxt=view.findViewById(R.id.pesoTxt);
        getDatosBtn= view.findViewById(R.id.getDatos);
    }

}

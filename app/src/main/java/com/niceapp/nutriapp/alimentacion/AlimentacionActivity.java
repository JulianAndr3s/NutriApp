package com.niceapp.nutriapp.alimentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.niceapp.nutriapp.R;
import com.niceapp.nutriapp.facade.AlimentacionFacade;
import com.niceapp.nutriapp.util.GeneraRandom;
import com.niceapp.nutriapp.util.ViewUtil;

import java.util.ArrayList;

public class AlimentacionActivity extends AppCompatActivity {

    private ViewUtil viewUtil;
    private Button getDatosDesayunoBtn;

    FirebaseDatabase database;
    DatabaseReference dbRef;

    private TextView dietaDiariaTxt;

    private ListView dataListView;

    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    AlimentacionFacade alimentacionFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacion);
        initComponents();

    }

    private void initComponents() {
        dietaDiariaTxt = findViewById(R.id.dietaDiariaTxt);
        viewUtil = new ViewUtil(this);
        viewUtil.setToolBar("Alimentacion");
        dataListView = findViewById(R.id.dataListView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked);
        dataListView.setAdapter(adapter);
        getDatosDesayunoBtn = findViewById(R.id.getDatosDesayunoBtn);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference().child("Recetas");
        alimentacionFacade = new AlimentacionFacade(dbRef);


    }


    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add(
                        (String) dataSnapshot.getValue());

                listKeys.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbRef.addChildEventListener(childListener);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void getDatosDesayuno(View view) {
        adapter.clear();
        dbRef = alimentacionFacade.getDesayuno();
        addChildEventListener();
    }


    public void getDatosAlmuerzo(View view) {
        adapter.clear();
        dbRef = alimentacionFacade.getAlmuerzo();
        addChildEventListener();
    }

    public void getDatosComida(View view) {
        adapter.clear();
        dbRef = alimentacionFacade.getComida();
        addChildEventListener();
    }
}
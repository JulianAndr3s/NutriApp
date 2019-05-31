package com.niceapp.nutriapp.consejos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niceapp.nutriapp.R;
import com.niceapp.nutriapp.util.GeneraRandom;
import com.niceapp.nutriapp.util.ViewUtil;

public class ConsejosActivity extends AppCompatActivity {

    private ViewUtil viewUtil;
    TextView consejosTxt;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        initComponents();


        databaseReference.child("Consejos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String consejo = dataSnapshot.child(GeneraRandom.getRandomConsejos()).getValue().toString();
                    consejosTxt.setText(consejo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void initComponents() {
        consejosTxt = findViewById(R.id.consejosTxt);
        viewUtil = new ViewUtil(this);
        viewUtil.setToolBar("Consejos");
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

package com.niceapp.nutriapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niceapp.nutriapp.consejos.ConsejosActivity;
import com.niceapp.nutriapp.datosusuario.IngresoDatosActivity;
import com.niceapp.nutriapp.fragment.AlimentacionFragment;
import com.niceapp.nutriapp.modelo.Persona;
import com.niceapp.nutriapp.nosotros.ContactoActivity;
import com.niceapp.nutriapp.nosotros.NosotrosActivity;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText usuarioTxt, edadTxt, pesoTxt, estaturaTxt;
    Button guardarBtn;
    private String usuarioE, idUser;
    private int edadE, estaturaE;
    private double pesoE;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usuarioE = getIntent().getStringExtra("userName");
        idUser = getIntent().getStringExtra("idUser");
        initComponents();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("persona").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists() || dataSnapshot == null) {
                    initComponents();
                } else {
                    ocultarElementos();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void initComponents() {

        edadTxt = findViewById(R.id.edadTxt);
        pesoTxt = findViewById(R.id.pesoTxt);
        estaturaTxt = findViewById(R.id.estaturaTxt);
        guardarBtn = findViewById(R.id.guardar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_alimentacion) {
            fragment = new AlimentacionFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_home, fragment).addToBackStack(null).commit();
        } else if (id == R.id.nav_consejos) {
            Intent intent = new Intent(Home.this, ConsejosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_datos) {
            Intent intent = new Intent(Home.this, IngresoDatosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contacta) {
            Intent intent = new Intent(Home.this, ContactoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_nosotros) {
            Intent intent = new Intent(Home.this, NosotrosActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void guardar(View view) {

        estaturaE = Integer.parseInt(estaturaTxt.getText().toString());
        edadE = Integer.parseInt(edadTxt.getText().toString());
        pesoE = Double.parseDouble(pesoTxt.getText().toString());
        Persona persona = new Persona(usuarioE, edadE, pesoE, estaturaE);
        databaseReference.child("persona").child(idUser).setValue(persona);
        Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_LONG).show();
        //ocultarElementos();
    }

    private void ocultarElementos() {

        estaturaTxt.setVisibility(View.GONE);
        edadTxt.setVisibility(View.GONE);
        pesoTxt.setVisibility(View.GONE);
        guardarBtn.setVisibility(View.GONE);

    }


}

package com.niceapp.nutriapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niceapp.nutriapp.alimentacion.AlimentacionActivity;
import com.niceapp.nutriapp.consejos.ConsejosActivity;
import com.niceapp.nutriapp.datosusuario.IngresoDatosActivity;
import com.niceapp.nutriapp.modelo.Persona;
import com.niceapp.nutriapp.nosotros.ContactoActivity;
import com.niceapp.nutriapp.nosotros.NosotrosActivity;


//OnCreate

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String user2 = "names";
    private TextView txtUser;

    private EditText usuarioTxt, edadTxt, pesoTxt, estaturaTxt;
    Button guardarBtn;
    private String usuarioE, idUser;
    private int edadE, estaturaE;
    private double pesoE;

    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();
        ocultarElementos();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            usuarioE = user.getDisplayName();
            idUser = user.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference();
            userExistOnSystem(idUser);
            //initComponents();
        } else {
            goLoginScreen();
        }
        txtUser = (TextView) findViewById(R.id.txtUser);
        String user2 = getIntent().getStringExtra("names");
        txtUser.setText("¡Bienvenido " + user2 + "!");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void userExistOnSystem(String idUser) {
        databaseReference.child("persona").child(idUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Toast.makeText(getApplicationContext(), "No es necesario cambiar la informacion", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe ingresar informacion", Toast.LENGTH_LONG).show();
                    mostrarElementos();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void mostrarElementos() {
        estaturaTxt.setVisibility(View.VISIBLE);
        edadTxt.setVisibility(View.VISIBLE);
        pesoTxt.setVisibility(View.VISIBLE);
        guardarBtn.setVisibility(View.VISIBLE);
    }


    public void guardar(View view) {

        estaturaE = Integer.parseInt(estaturaTxt.getText().toString());
        edadE = Integer.parseInt(edadTxt.getText().toString());
        pesoE = Double.parseDouble(pesoTxt.getText().toString());
        Persona persona = new Persona(usuarioE, edadE, pesoE, estaturaE);
        databaseReference.child("persona").child(idUser).setValue(persona);
        Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_LONG).show();
        ocultarElementos();
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initComponents() {
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

        if (id == R.id.nav_consejos) {
            Intent intent = new Intent(Home.this, ConsejosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_alientacion) {
            Intent intent = new Intent(Home.this, AlimentacionActivity.class);
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

    private void ocultarElementos() {
        estaturaTxt.setVisibility(View.GONE);
        edadTxt.setVisibility(View.GONE);
        pesoTxt.setVisibility(View.GONE);
        guardarBtn.setVisibility(View.GONE);
    }


    public void cerrarSesion(View view) {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
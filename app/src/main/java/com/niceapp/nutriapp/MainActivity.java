package com.niceapp.nutriapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.niceapp.nutriapp.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtCorreo;
    private TextView txtContraseña;
    private Button btnIniciarSesion;

    public CallbackManager callbackManager;
    private LoginButton loginButton;
    private static final String EMAIL = "email";
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        btnIniciarSesion.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton=(LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreem();
                Toast.makeText(getApplicationContext(),"Siii",Toast.LENGTH_LONG);
                FirebaseUser currentUser = mAuth.getCurrentUser();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Se cancelo la operación", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Revisa tu conexión a internet", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initComponents() {
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
    }

    private void loguearUsuario() {
        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = txtCorreo.getText().toString().trim();
        String password = txtContraseña.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {//(precio.equals(""))
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();

        //loguear usuario
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(MainActivity.this, "Bienvenido: " + txtCorreo.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(), Home.class);
                            intencion.putExtra(Home.user, user);
                            startActivity(intencion);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(MainActivity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        loguearUsuario();
    }

    private void goMainScreem() {
        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void goToRegistroActivity(View view) {
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

}
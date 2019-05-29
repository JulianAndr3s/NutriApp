package com.niceapp.nutriapp.registro;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.niceapp.nutriapp.R;
import com.niceapp.nutriapp.util.ViewUtil;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewUtil viewUtil;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCorreo;
    private EditText txtContraseña;
    private EditText txtContraseña2;
    private EditText txtFechaNacimiento;
    private Button btnRegistrar;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        viewUtil = new ViewUtil(this);
        viewUtil.setToolBar("Registro");
        initComponents();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        btnRegistrar.setOnClickListener(this);
    }

    private void initComponents() {

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContraseña = findViewById(R.id.txtContraseña);
        txtContraseña2 = findViewById(R.id.txtContraseña2);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        btnRegistrar = findViewById(R.id.btnRegistrar);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = txtCorreo.getText().toString().trim();
        String password  = txtContraseña.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(RegistroActivity.this,"Se ha registrado el usuario con el email: "+ txtCorreo.getText(),Toast.LENGTH_LONG).show();
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(RegistroActivity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(RegistroActivity.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        //Invocamos al método:
        registrarUsuario();
    }
}

package com.example.ejemplofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText correo,clave;
    private Button btn_login;
    private FirebaseAuth mAuth;
    private View btn_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.txt_correo);
        clave = findViewById(R.id.txt_clave);
        btn_login = findViewById(R.id.btn_login);
        btn_registro = findViewById(R.id.btn_registro);
        btn_login.setOnClickListener(this);
        btn_registro.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        correo.requestFocus();
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_registro:
                irARegistro();
                break;
        }
    }

    private void irARegistro(){
        Intent ir_a_registro = new Intent(Login.this,Registro.class);
        startActivity(ir_a_registro);
    }

    private void login() {
        String txt_correo,txt_clave;

        txt_correo = correo.getText().toString();
        txt_clave = clave.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(txt_correo,txt_clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    correo.setText("");
                    clave.setText("");
                    Intent siguiente = new Intent(Login.this,Home.class);
                    Toast.makeText(Login.this, "Logueado con exito", Toast.LENGTH_SHORT).show();
                    startActivity(siguiente);
                }
                else{
                    Toast.makeText(Login.this, "Error, verifica los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
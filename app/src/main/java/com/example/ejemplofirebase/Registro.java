package com.example.ejemplofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemplofirebase.mensajes.MensajesAplicacion;
import com.example.ejemplofirebase.modelo.Constantes;
import com.example.ejemplofirebase.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private EditText txt_correo,txt_clave1,txt_clave2;
    private EditText txt_nombre,txt_apellido;
    private Button btn_registro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txt_nombre =findViewById(R.id.txt_nombre);
        txt_apellido = findViewById(R.id.txt_Apellido);
        txt_correo = findViewById(R.id.txt_correo);
        txt_clave1 = findViewById(R.id.txt_clave);
        txt_clave2 = findViewById(R.id.clave2);
        btn_registro = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();
        btn_registro.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        txt_nombre.requestFocus();
    }

    @Override
    public void onClick(View v) {
        String nombre,apellido,correo,clave1,clave2;

        nombre = txt_nombre.getText().toString();
        apellido = txt_apellido.getText().toString();
        correo = txt_correo.getText().toString();
        clave1 = txt_clave1.getText().toString();
        clave2 = txt_clave2.getText().toString();

        if(correo.length()>0){
            if(clave1.equals(clave2) && clave1.length()>=6)
            {
                if(!nombre.isEmpty() && !apellido.isEmpty())
                {
                    registrarUsuario(nombre,apellido,correo,clave1);
                    return;
                }
            }
        }
        Toast.makeText(Registro.this,MensajesAplicacion.VALIDAR_DATOS,Toast.LENGTH_LONG).show();
    }

    private void registrarUsuario(String nombre, String apellido, String correo, String clave){

        mAuth.createUserWithEmailAndPassword(correo,clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String uid;
                if(task.isSuccessful())
                {
                    Toast.makeText(Registro.this,MensajesAplicacion.EXITO_USUARIO,Toast.LENGTH_LONG).show();
                    //TODO: Guardar datos complementarios del registro
                    uid = task.getResult().getUser().getUid();
                    guardarDatosUsuario(new Usuario(uid,nombre,apellido,correo,clave));
                }
                else
                {
                    Toast.makeText(Registro.this, MensajesAplicacion.ERROR_AL_CREAR_USUARIO,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void guardarDatosUsuario(Usuario usuario)
    {
        FirebaseDatabase db;
        DatabaseReference dr1,dr2;

        db = FirebaseDatabase.getInstance();
        dr1 = db.getReference(usuario.getUid()).child(Constantes.DATOS);
        //Ingresar
        dr2 = dr1.push();
        dr2.setValue(usuario);
        dr2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Intent irAHome;
                    resetearCampos();

                    irAHome = new Intent(Registro.this,Home.class);
                    startActivity(irAHome);
                }
                else{
                    //TODO Mensaje los datos no se guardaron, pero el usuario si se creo
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void resetearCampos(){
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_correo.setText("");
        txt_clave1.setText("");
        txt_clave2.setText("");
    }
}
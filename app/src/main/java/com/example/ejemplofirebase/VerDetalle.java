package com.example.ejemplofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejemplofirebase.modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerDetalle extends AppCompatActivity {

    private Button btn_volver;
    private String llaves[];
    private DatabaseReference dr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle);
        btn_volver = findViewById(R.id.btn_volver_detalle);

        //dr1 = db.getReference(usuario.getUid()).child("datos").child("-MqRQ-ZFhw7-BwaIOBKY");

        llaves = getIntent().getExtras().getStringArray("datos");

        FirebaseDatabase db;
        db = FirebaseDatabase.getInstance();

        System.out.println(llaves[0]+" "+llaves[1]);

        dr1 = db.getReference(llaves[0]).child("datos").child(llaves[1]);
        dr1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                llenarCamposUI(snapshot.getValue(Usuario.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_volver.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent volver = new Intent(VerDetalle.this,Home.class);
                startActivity(volver);
            }
        });
    }

    public void llenarCamposUI(Usuario usuario){
        TextView nombre,apellido,clave,correo;
        nombre = findViewById(R.id.txt_nombre_detalle);
        apellido = findViewById(R.id.txt_apellido_detalle);
        correo = findViewById(R.id.txt_correo_detalle);
        clave = findViewById(R.id.txt_clave_detalle);
        clave.setEnabled(false);
        nombre.setText(usuario.getNombre());
        apellido.setText(usuario.getApellido());
        correo.setText(usuario.getCorreo());
        clave.setText(usuario.getClave());
        /*//Editar
        HashMap map = new HashMap();
        map.put("apellido","Rave");
        map.put("clave","567891");
        dr1.updateChildren(map);*/
    }

}
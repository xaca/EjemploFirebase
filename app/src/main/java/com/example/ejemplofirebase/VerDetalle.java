package com.example.ejemplofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

import java.util.HashMap;

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

        llaves = getIntent().getExtras().getStringArray("id");

        FirebaseDatabase db;
        db = FirebaseDatabase.getInstance();
        dr1 = db.getReference(llaves[0]).child("datos").child(llaves[1]);
        dr1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getChildren());
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

    public void actualizarCampos(){
        //Editar
        HashMap map = new HashMap();
        map.put("apellido","Rave");
        map.put("clave","567891");
        dr1.updateChildren(map);
    }

}
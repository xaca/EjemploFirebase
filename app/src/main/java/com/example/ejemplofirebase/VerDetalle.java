package com.example.ejemplofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.core.view.View;

public class VerDetalle extends AppCompatActivity {

    private Button btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle);
        btn_volver = findViewById(R.id.btn_volver_detalle);
        btn_volver.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent volver = new Intent(VerDetalle.this,Home.class);
                startActivity(volver);
            }
        });
    }

}
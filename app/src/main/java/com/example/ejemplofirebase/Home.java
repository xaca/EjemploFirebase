package com.example.ejemplofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cerrar_sesion;
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseDatabase db;
        DatabaseReference dr1,dr2;
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_cerrar_sesion = findViewById(R.id.btn_cerrar_sesion);
        usuario = FirebaseAuth.getInstance().getCurrentUser();

        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(Home.this,Login.class);
                mAuth.signOut();
                startActivity(volver);
            }
        });

        ll = findViewById(R.id.contenedor);

        db = FirebaseDatabase.getInstance();
        dr1 = db.getReference(usuario.getUid()).child("datos");
        //dr1 = db.getReference(usuario.getUid()).child("datos").child("-MqRQ-ZFhw7-BwaIOBKY");
        dr1.addValueEventListener(new ValueEventListener() {
            //listar
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario;//Modelo de datos
                TextView temp;
                LinearLayout lh;
                Button btn;
                int cont = 0;

                for(DataSnapshot datos:snapshot.getChildren()){
                    System.out.println(datos.getKey());
                    usuario = datos.getValue(Usuario.class);
                    temp = new TextView(Home.this);
                    temp.setText(usuario.getNombre()+" "+ usuario.getApellido());
                    temp.setTextColor(cont++%2==0?Color.RED:Color.BLUE);
                    lh = new LinearLayout(Home.this);
                    lh.setOrientation(LinearLayout.HORIZONTAL);
                    lh.addView(temp);
                    btn = new Button(Home.this);
                    btn.setTag(datos.getKey());
                    btn.setText("Ver detalle");
                    btn.setOnClickListener(Home.this);
                    lh.addView(btn);
                    ll.addView(lh);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Ingresar
        //dr2 = dr1.push();
        //dr2.setValue(new Usuario("Giorgio","Tobon","g@a.com","654321"));


    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(Home.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
        Intent ver_detalle = new Intent(Home.this,VerDetalle.class);
        //ver_detalle.putExtra("id",v.getTag().toString());
        ver_detalle.putExtra("datos",new String[]{usuario.getUid(),v.getTag().toString()});
        startActivity(ver_detalle);
    }
}
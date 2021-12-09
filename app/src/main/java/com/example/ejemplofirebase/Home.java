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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Home extends AppCompatActivity {

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
        dr1 = db.getReference(usuario.getUid()).child("datos").child("-MqRQ-ZFhw7-BwaIOBKY");
        /*dr1.addValueEventListener(new ValueEventListener() {
            //listar
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario;//Modelo de datos
                TextView temp;
                int cont = 0;

                for(DataSnapshot datos:snapshot.getChildren()){
                    usuario = datos.getValue(Usuario.class);
                    temp = new TextView(Home.this);
                    temp.setText(usuario.getNombre()+" "+ usuario.getApellido()+" "+ usuario.getCorreo());
                    temp.setTextColor(cont++%2==0?Color.RED:Color.BLUE);
                    ll.addView(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        //Ingresar
        //dr2 = dr1.push();
        //dr2.setValue(new Usuario("Giorgio","Tobon","g@a.com","654321"));

        /*
        //Editar
        HashMap map = new HashMap();
        map.put("apellido","Rave");
        map.put("clave","567891");
        dr1.updateChildren(map);*/
    }
}
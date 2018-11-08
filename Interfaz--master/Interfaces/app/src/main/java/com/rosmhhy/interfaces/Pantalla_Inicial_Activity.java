package com.rosmhhy.interfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pantalla_Inicial_Activity extends AppCompatActivity {

    @BindView(R.id.btn_formulario)
    Button bt_formulario;
    @BindView(R.id.cerrar_cesion)
    Button bt_Cerrar_sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__inicial_);
        ButterKnife.bind(this);


        bt_formulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pantalla_Inicial_Activity.this, Formulario_Activity.class);
                startActivity(intent);
            }
        });


        bt_Cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Pantalla_Inicial_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

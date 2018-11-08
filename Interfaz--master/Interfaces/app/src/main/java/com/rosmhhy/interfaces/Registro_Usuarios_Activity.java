package com.rosmhhy.interfaces;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Registro_Usuarios_Activity extends AppCompatActivity {

    @BindView(R.id.Correo_Electronico)
    EditText Correo_Electronico;
    @BindView(R.id.Contraseña)
    EditText Contraseña;
    @BindView(R.id.apellido)
    EditText apelli;
    @BindView(R.id.nombre)
    EditText name;
    @BindView(R.id.Crear)
    Button CrearUsuario;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__usuarios_);
        auth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        CrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correo = Correo_Electronico.getText().toString();
                String contraseña = Contraseña.getText().toString();
                String nam = name.getText().toString();
                String apellifo = apelli.getText().toString();

                auth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(Registro_Usuarios_Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            sendEmailVerification();
                            String user_id = auth.getCurrentUser().getUid();
                            String Email = auth.getCurrentUser().getEmail();
                            String Apellido = apelli.getText().toString();
                            String nombre = name.getText().toString();
                            Clase_Datos claseDatosRegistro = new Clase_Datos(nombre,Apellido,Email);
                            databaseReference.child("Users").child(user_id).setValue(claseDatosRegistro);
                        } else  {
                            Toast.makeText(getApplicationContext(), "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public void sendEmailVerification() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "se envio el correo de verificacion", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


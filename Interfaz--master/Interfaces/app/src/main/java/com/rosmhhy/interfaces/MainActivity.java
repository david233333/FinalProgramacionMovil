
package com.rosmhhy.interfaces;

import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.CorreoElectronico)
    EditText email;
    @BindView(R.id.Contraseña)
    EditText password;
    @BindView(R.id.ingreso)
    Button login;
    @BindView(R.id.registro)
    Button register;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener firebaseL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, Pantalla_Inicial_Activity.class);
            Toast.makeText(getApplicationContext(), "sesion activa con el usuario "+ user.getEmail(), Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(getApplicationContext(), "No has iniciado Sesion", Toast.LENGTH_SHORT).show();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String userE =  email.getText().toString();
                 String pass = password.getText().toString();

                auth.signInWithEmailAndPassword(userE,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, Pantalla_Inicial_Activity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplication(),"Correo Electronico o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registro_Usuarios_Activity.class);
                startActivity(intent);
            }
        });
    }


}


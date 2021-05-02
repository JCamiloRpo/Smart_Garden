package com.example.smartgarden.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.entities.Sesion;
import com.example.smartgarden.ui.fragments.HomeFragment;

public class LoginActivity extends AppCompatActivity {
    EditText txtUsuario, txtPassword;
    Button btnIngresar;
    TextView btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpView();
    }

    private void setUpView(){
        txtUsuario = findViewById(R.id.TxtUsuario);
        txtPassword = findViewById(R.id.TxtPassword);

        btnRegistrar = findViewById(R.id.TxtRegistro);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnIngresar = findViewById(R.id.BtnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString(), password = txtPassword.getText().toString();
                if(usuario.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar un usuario.", Toast.LENGTH_SHORT).show();
                else if(password.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar una contraseña.", Toast.LENGTH_SHORT).show();
                else{
                    String[][] reg = SplashActivity.sql.read(ConexionSQLite.TABLE_USUARIO, "Usuario='"+usuario+"'");
                    if(reg.length>0){
                        if(password.equals(reg[0][3])){
                            HomeFragment.usuarioID = Long.parseLong(reg[0][0]);
                            HomeFragment.usuario = usuario;

                            SplashActivity.sesionID = SplashActivity.sql.insert(new Sesion(Long.parseLong(reg[0][0]), usuario));

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrecto.", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrecto.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
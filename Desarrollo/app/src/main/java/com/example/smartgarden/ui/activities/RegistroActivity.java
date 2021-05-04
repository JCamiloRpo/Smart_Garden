package com.example.smartgarden.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.entities.Sesion;
import com.example.smartgarden.ui.entities.Usuario;
import com.example.smartgarden.ui.fragments.HomeFragment;

public class RegistroActivity extends AppCompatActivity {
    EditText txtNombre, txtUsuario, txtPassword, txtCorreo;
    Button btnRegistrar;
    ImageButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        setUpView();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void setUpView(){
        txtNombre = findViewById(R.id.TxtNombreR);
        txtUsuario = findViewById(R.id.TxtUsuarioR);
        txtPassword = findViewById(R.id.TxtPasswordR);
        txtCorreo = findViewById(R.id.TxtCorreoR);

        btnRegistrar = findViewById(R.id.BtnRegistrarse);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString().trim(), usuario = txtUsuario.getText().toString().trim(),
                        password = txtPassword.getText().toString().trim(), correo = txtCorreo.getText().toString().trim(),
                        regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";

                if(nombre.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar un nombre.", Toast.LENGTH_SHORT).show();
                else if(usuario.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar un usuario (con el iniciará sesión las proximas veces).", Toast.LENGTH_SHORT).show();
                else if(SplashActivity.sql.read(ConexionSQLite.TABLE_USUARIO, "Usuario='"+usuario+"'").length > 0)
                    Toast.makeText(getApplicationContext(), "El usuario ya existe.", Toast.LENGTH_SHORT).show();
                else if(password.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar una contraseña.", Toast.LENGTH_SHORT).show();
                else if(password.length() < 6)
                    Toast.makeText(getApplicationContext(), "La contraseña debe tener mas de 6 caracteres.", Toast.LENGTH_SHORT).show();
                else if(correo.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar un correo.", Toast.LENGTH_SHORT).show();
                else if(!correo.matches(regex))
                    Toast.makeText(getApplicationContext(), "Ingresa un correo valido.", Toast.LENGTH_SHORT).show();
                else {
                    long id = SplashActivity.sql.insert(new Usuario(nombre, usuario, password, correo));
                    HomeFragment.usuarioID = id;
                    HomeFragment.usuario = usuario;

                    SplashActivity.sesionID = SplashActivity.sql.insert(new Sesion(id, usuario));

                    Intent i = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                    finish();
                }
            }
        });

        btnVolver = findViewById(R.id.BtnVolverR);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
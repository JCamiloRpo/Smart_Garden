package com.example.smartgarden.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.entities.Sesion;
import com.example.smartgarden.ui.entities.Usuario;
import com.example.smartgarden.ui.fragments.HomeFragment;

public class RegistroActivity extends AppCompatActivity {
    EditText txtNombre, txtUsuario, txtPassword, txtCorreo;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        setUpView();
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
                String nombre = txtNombre.getText().toString(), usuario = txtUsuario.getText().toString(),
                        password = txtPassword.getText().toString(), correo = txtCorreo.getText().toString();
                if(nombre.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar un nombre.", Toast.LENGTH_SHORT).show();
                else if(usuario.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar un usuario (con el iniciará sesión las proximas veces).", Toast.LENGTH_SHORT).show();
                else if(password.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar una contraseña.", Toast.LENGTH_SHORT).show();
                else if(correo.equals(""))
                    Toast.makeText(getApplicationContext(), "Debe ingresar un correo.", Toast.LENGTH_SHORT).show();
                else if(SplashActivity.sql.read(ConexionSQLite.TABLE_USUARIO, "Usuario='"+usuario+"'").length > 0)
                    Toast.makeText(getApplicationContext(), "El usuario ya existe.", Toast.LENGTH_SHORT).show();
                else {
                    long id = SplashActivity.sql.insert(new Usuario(nombre, usuario, password, correo));
                    HomeFragment.usuarioID = id;
                    HomeFragment.usuario = usuario;

                    SplashActivity.sql.insert(new Sesion(id, usuario));

                    Intent i = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
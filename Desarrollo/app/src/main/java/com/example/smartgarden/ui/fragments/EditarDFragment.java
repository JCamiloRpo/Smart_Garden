package com.example.smartgarden.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.activities.MainActivity;
import com.example.smartgarden.ui.activities.SplashActivity;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.entities.Sesion;
import com.example.smartgarden.ui.entities.Usuario;

public class EditarDFragment extends Fragment {
    EditText txtUsuario, txtNombre, txtCorreo,  txtPassword,  txtPasswordConfirm;
    Button btnGuardar;

    public EditarDFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editard, container, false);

        setUpView(root);
        return root;
    }

    private void setUpView(View v){
        txtUsuario = v.findViewById(R.id.TxtUsuarioE);
        txtNombre = v.findViewById(R.id.TxtNombreE);
        txtCorreo = v.findViewById(R.id.TxtCorreoE);
        txtPassword = v.findViewById(R.id.TxtPasswordE);
        txtPasswordConfirm = v.findViewById(R.id.TxtPasswordConfirmE);

        String[][] reg = SplashActivity.sql.read(ConexionSQLite.TABLE_USUARIO, "ID="+HomeFragment.usuarioID);
        txtNombre.setText(reg[0][1]);
        txtUsuario.setText(reg[0][2]);
        txtPassword.setText(reg[0][3]);
        txtCorreo.setText(reg[0][4]);

        btnGuardar = v.findViewById(R.id.BtnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString(), usuario = txtUsuario.getText().toString(),
                        password = txtPassword.getText().toString(), correo = txtCorreo.getText().toString(),
                        passwordconfirm = txtPasswordConfirm.getText().toString();
                if(nombre.equals(""))
                    Toast.makeText(getContext(), "Debe ingresar un nombre.", Toast.LENGTH_SHORT).show();
                else if(password.equals(""))
                    Toast.makeText(getContext(), "Debe ingresar una contraseña.", Toast.LENGTH_SHORT).show();
                else if(!password.equals(passwordconfirm))
                    Toast.makeText(getContext(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                else if(correo.equals(""))
                    Toast.makeText(getContext(), "Debe ingresar un correo.", Toast.LENGTH_SHORT).show();
                else {
                    SplashActivity.sql.update(new Usuario(nombre, usuario, password, correo), HomeFragment.usuarioID);
                    Navigation.findNavController(v).popBackStack();
                }
            }
        });
    }
}
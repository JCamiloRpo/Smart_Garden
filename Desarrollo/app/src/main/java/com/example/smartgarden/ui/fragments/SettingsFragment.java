package com.example.smartgarden.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.activities.LoginActivity;
import com.example.smartgarden.ui.activities.SplashActivity;
import com.example.smartgarden.ui.conexions.ConexionSQLite;

public class SettingsFragment extends Fragment {
    Button btnEditarD, btnCerrarS;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        setUpView(root);
        return root;
    }

    private void setUpView(View v){
        btnEditarD = v.findViewById(R.id.BtnEditarDatos);
        btnEditarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_settings_to_editarD);
            }
        });

        btnCerrarS = v.findViewById(R.id.BtnCerrarSesion);
        btnCerrarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SplashActivity.sql.delete(ConexionSQLite.TABLE_SESION, SplashActivity.sesionID);

                Intent i = new Intent(getContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });
    }

}
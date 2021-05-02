package com.example.smartgarden.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.smartgarden.R;
import com.example.smartgarden.ui.adapters.NotificacionAdapter;
import com.example.smartgarden.ui.entities.Notificacion;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    ListView list;
    NotificacionAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        setUpView(root);
        return root;
    }

    private void setUpView(View v){
        list = v.findViewById(R.id.ListNoti);
        //Consultar datos

        adapter = new NotificacionAdapter(getActivity(), pruebaDatos());
        list.setAdapter(adapter);
    }

    private ArrayList<Notificacion> pruebaDatos(){
        ArrayList<Notificacion> noti = new ArrayList<>();
        noti.add(new Notificacion(1, R.drawable.noti_abonar2, "Abonar área 2"));
        noti.add(new Notificacion(2, R.drawable.noti_analisis1, "Analisis área 3"));
        noti.add(new Notificacion(3, R.drawable.noti_cosechar1, "Cosechar área 1"));
        noti.add(new Notificacion(4, R.drawable.noti_podar1, "Podar área 2"));
        noti.add(new Notificacion(5, R.drawable.noti_regar1, "Regar área 1"));
        noti.add(new Notificacion(6, R.drawable.noti_podar2, "Podar área 3"));
        noti.add(new Notificacion(7, R.drawable.noti_abonar2, "Abonar área 1"));
        noti.add(new Notificacion(8, R.drawable.noti_analsis2, "Analisis área 2"));
        noti.add(new Notificacion(9, R.drawable.noti_regar2, "Regar área 3"));
        noti.add(new Notificacion(10, R.drawable.noti_cosechar2, "Cosechar área 2"));
        return noti;
    }
}
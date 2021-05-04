package com.example.smartgarden.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.smartgarden.R;
import com.example.smartgarden.ui.activities.NotiDetalleActivity;
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
        noti.add(new Notificacion(1, 1, R.drawable.noti_abonar2, "Abonar área 2", "Es posible que tus cultivos no crezcan con mucha fuerza. Si esto pasara, mezcla o cubre el suelo con abonos orgánicos."));
        noti.add(new Notificacion(2, 2, R.drawable.noti_analisis1, "Analisis área 3", "El análisis de tu huerta ya esta disponible en la sección de análisis, pégale una mirada, para que veas como avanza tu huerta"));
        noti.add(new Notificacion(3, 3, R.drawable.noti_cosechar1, "Cosechar área 1", "Despues de todo su arduo trabajo, tiempo y dedicación al cultivar sus frutas y verduras durante la temporada, recordad cuándo debe cosecharlas es tan importante como cultivarlas."));
        noti.add(new Notificacion(4, 4, R.drawable.noti_podar1, "Podar área 2", "La poda se inica con un corte, hasta casi la mitad de su diametro, del lado inferior y a unos 2-3 cm del tronco del cual se origina."));
        noti.add(new Notificacion(5, 5, R.drawable.noti_regar1, "Regar área 1", "Intenta no mojar demasiado las partes aéreas de las plantas ya que el exceso de humedad en las flores y tallos puede propiciar que aparezcan hongos y otras plagas"));
        noti.add(new Notificacion(6, 4, R.drawable.noti_podar2, "Podar área 3", "La poda se inica con un corte, hasta casi la mitad de su diametro, del lado inferior y a unos 2-3 cm del tronco del cual se origina."));
        noti.add(new Notificacion(7, 1, R.drawable.noti_abonar2, "Abonar área 1", "Es posible que tus cultivos no crezcan con mucha fuerza. Si esto pasara, mezcla o cubre el suelo con abonos orgánicos."));
        noti.add(new Notificacion(8, 2, R.drawable.noti_analsis2, "Analisis área 2", "El análisis de tu huerta ya esta disponible en la sección de análisis, pégale una mirada, para que veas como avanza tu huerta"));
        noti.add(new Notificacion(9, 5, R.drawable.noti_regar2, "Regar área 3", "Intenta no mojar demasiado las partes aéreas de las plantas ya que el exceso de humedad en las flores y tallos puede propiciar que aparezcan hongos y otras plagas"));
        noti.add(new Notificacion(10, 3, R.drawable.noti_cosechar2, "Cosechar área 2", "Despues de todo su arduo trabajo, tiempo y dedicación al cultivar sus frutas y verduras durante la temporada, recordad cuándo debe cosecharlas es tan importante como cultivarlas."));
        NotiDetalleActivity.setNoti(noti);
        return noti;
    }
}
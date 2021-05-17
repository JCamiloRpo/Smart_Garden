package com.example.smartgarden.ui.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.smartgarden.R;
import com.example.smartgarden.ui.activities.NotiDetalleActivity;
import com.example.smartgarden.ui.adapters.AnalisisAdapter;
import com.example.smartgarden.ui.adapters.NotificacionAdapter;
import com.example.smartgarden.ui.conexions.ConexionAPI;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.entities.Notificacion;

import org.json.JSONException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Random;

import static com.example.smartgarden.ui.activities.SplashActivity.*;

public class NotificationsFragment extends Fragment {
    ListView list;
    NotificacionAdapter adapter;
    TextView txtNoData;
    ProgressBar progress;
    ArrayList<Notificacion> noti;
    boolean data;
    ConnectTask descarga = new ConnectTask();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        setUpView(root);
        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        descarga.cancel(true);
    }

    private void setUpView(View v){
        list = v.findViewById(R.id.ListNoti);
        list.setVisibility(View.GONE);
        txtNoData = v.findViewById(R.id.TxtNoData);
        txtNoData.setVisibility(View.GONE);
        progress = v.findViewById(R.id.Progress);
        progress.setVisibility(View.VISIBLE);

        descarga.execute();
    }

    private void finish(){
        progress.setVisibility(View.GONE);

        adapter = new NotificacionAdapter(getActivity(), noti);
        list.setAdapter(adapter);

        if (data) {
            txtNoData.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }
        else {
            txtNoData.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }
    }

    private ArrayList<Notificacion> consultarDatos(){
        ArrayList<Notificacion> noti = new ArrayList<>();
        String[][] notiDB;
        boolean connectAPI = api.isConnected();
        try {
            if (connectAPI){
                sql.clear(ConexionSQLite.TABLE_NOTIFICACION); // Limpiar tabla local
                notiDB = api.getData(ConexionAPI.TABLE_NOTIFICACION);   // Obtener datos
            }
            else {
                notiDB = sql.read(ConexionSQLite.TABLE_NOTIFICACION);
            }
            for (int i=0; i<notiDB.length; i++){    // Recorrer datos
                Notificacion n = new Notificacion(Long.parseLong(notiDB[i][0]), Integer.parseInt(notiDB[i][1]),
                        notiDB[i][2], notiDB[i][3], Integer.parseInt(notiDB[i][4]));

                if (connectAPI) sql.insert(n);  // Agregar a la tabla local

                if (n.getEstado() == 1){
                    int img = R.drawable.notificacion;
                    // tipo 1-Abonar, 2-Analisis, 3-Cosechar, 4-Podar, 5-Regar
                    switch (n.getTipo()){
                        case 1: img = new Random().nextInt(2) == 0 ? R.drawable.noti_abonar1 : R.drawable.noti_abonar2;
                            break;
                        case 2: img = new Random().nextInt(2) == 0 ? R.drawable.noti_analisis1 : R.drawable.noti_analsis2;
                            break;
                        case 3: img = new Random().nextInt(2) == 0 ? R.drawable.noti_cosechar1 : R.drawable.noti_cosechar2;
                            break;
                        case 4: img = new Random().nextInt(2) == 0 ? R.drawable.noti_podar1 : R.drawable.noti_podar2;
                            break;
                        case 5: img = new Random().nextInt(2) == 0 ? R.drawable.noti_regar1 : R.drawable.noti_regar2;
                            break;
                    }
                    n.setImg(img);
                    noti.add(n);    // Agregar a la lista
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        NotiDetalleActivity.setNoti(noti);
        data = !noti.isEmpty();
        return noti;
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

    /**
     * Clase interna para probar la conexión y no bloquear la pantalla
     */
    private class ConnectTask extends AsyncTask<String, Float, Integer> {

        /**
         * Lo ejecuta el hilo principal antes de que inicie el hilo hijo
         */
        @Override
        protected void onPreExecute() { }

        /**
         * Lo ejecuta el hilo hijo en segundo plano
         * @param params
         * @return
         */
        @Override
        protected Integer doInBackground(String[] params) {
            //Consultar datos
            noti = consultarDatos();
            return 0;
        }

        /**
         * Lo ejecuta el hilo hijo despues de terminar su codigo
         * @param result
         */
        @Override
        protected void onPostExecute(Integer result) { finish(); }

        /**
         * Si se llega cancelar por alguna razon para el hilo hijo
         * @param result
         */
        @Override
        protected void onCancelled(Integer result) { }

        /**
         * Lo ejecuta el hilo hijo por si se quiere mostrar una barra de progreso
         * @param values
         */
        @Override
        protected void onProgressUpdate(Float... values) { }

    }

}
package com.example.smartgarden.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.conexions.ConexionAPI;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.fragments.HomeFragment;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    public static ConexionSQLite sql;
    public static ConexionAPI api;
    public static long sesionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sql = new ConexionSQLite(this, "db_SmartGarden",null, 1);

        setUp();
    }

    private void setUp(){
        new ConnectTask().execute();

        TimerTask tarea;
        String[][] reg = sql.read(ConexionSQLite.TABLE_SESION);
        System.out.println(reg.length);
        if(reg.length>0){
            sesionID = Long.parseLong(reg[0][0]);
            HomeFragment.usuarioID = Long.parseLong(reg[0][1]);
            HomeFragment.usuario = reg[0][2];
            tarea = new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            };
        }
        else {
            tarea = new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            };
        }

        Timer t = new Timer();
        t.schedule(tarea, 3000);
    }

    /**
     * Clase interna para probar la conexión y no bloquear la pantalla
     */
    private class ConnectTask extends AsyncTask<String, Float, Integer> {

        /**
         * Lo ejecuta el hilo principal antes de que inicie el hilo hijo
         */
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Conectando...", Toast.LENGTH_SHORT).show();
        }

        /**
         * Lo ejecuta el hilo hijo en segundo plano
         * Para probar la conexion
         * @param params
         * @return
         */
        @Override
        protected Integer doInBackground(String[] params) {
            return connectAPI();
        }

        /**
         * Lo ejecuta el hilo hijo despues de terminar su codigo
         * @param result
         */
        @Override
        protected void onPostExecute(Integer result) {
            if(result==0)
                Toast.makeText(getApplicationContext(), "Conexion establecida",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "No hay conexion",Toast.LENGTH_SHORT).show();
        }

        /**
         * Si se llega cancelar por alguna razon para el hilo hijo
         * @param result
         */
        @Override
        protected void onCancelled(Integer result) {
            if(result==0)
                Toast.makeText(getApplicationContext(), "Conexion establecida",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "No hay conexion",Toast.LENGTH_SHORT).show();
        }

        /**
         * Lo ejecuta el hilo hijo por si se quiere mostrar una barra de progreso
         * @param values
         */
        @Override
        protected void onProgressUpdate(Float... values) {
        }

        /**
         * Metodo para establecer la conexion con el servidor MariaDB por medio de la clase de ConexionAPI
         */
        private int connectAPI(){
            //Me conecto con el servidor de base de datos
            String ftps, user, pass;
            try {
                api = new ConexionAPI(getString(R.string.server_api));
                System.out.println(api.isConnected());
                return api.isConnected() ? 0 : -1;
            } catch (Exception e) {
                cancel(true);
                e.printStackTrace();
            }
            return -1;
        }
    }

}
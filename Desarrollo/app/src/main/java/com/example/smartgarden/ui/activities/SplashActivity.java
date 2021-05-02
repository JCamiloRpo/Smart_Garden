package com.example.smartgarden.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.fragments.HomeFragment;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    public static ConexionSQLite sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sql = new ConexionSQLite(this, "db_SmartGarden",null, 1);

        setUp();
    }

    private void setUp(){
        TimerTask tarea;
        String[][] reg = sql.read(ConexionSQLite.TABLE_SESION);
        if(reg.length>0){
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
        t.schedule(tarea, 5000);
    }
}
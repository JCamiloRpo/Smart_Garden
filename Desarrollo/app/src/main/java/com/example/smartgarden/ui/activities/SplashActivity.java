package com.example.smartgarden.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.conexions.ConexionSQLite;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    public static ConexionSQLite sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sql = new ConexionSQLite(this, "db_SmartGarden",null, 1);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        };

        Timer t = new Timer();
        t.schedule(tarea, 5000);
    }
}
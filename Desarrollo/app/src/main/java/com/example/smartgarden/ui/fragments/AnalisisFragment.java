package com.example.smartgarden.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.smartgarden.R;
import com.example.smartgarden.ui.adapters.AnalisisAdapter;
import com.example.smartgarden.ui.conexions.ConexionAPI;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.entities.Analisis;
import com.example.smartgarden.ui.entities.Notificacion;
import com.example.smartgarden.ui.entities.Registro;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

import static com.example.smartgarden.ui.activities.SplashActivity.api;
import static com.example.smartgarden.ui.activities.SplashActivity.sql;

public class AnalisisFragment extends Fragment {
    ListView list;
    AnalisisAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_analisis, container, false);

        setUpView(root);
        return root;
    }

    private void setUpView(View v){
        Utils.init(getContext());
        list = v.findViewById(R.id.ListAnalisis);

        //Consultar datos
        adapter = new AnalisisAdapter(getActivity(), pruebaDatos());
        list.setAdapter(adapter);
    }

    private ArrayList<Analisis> pruebaDatos(){
        ArrayList<Analisis> analisis = new ArrayList<>();
        LineDataSet dataSet = new LineDataSet(valuesData(), "Datos 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSet.setLineWidth(20);
        dataSet.setColors(new int[]{R.color.green}, getContext());
        dataSet.setDrawCircles(true);
        dataSet.setCircleRadius(6f);
        dataSet.setCircleHoleRadius(10f);
        dataSet.setCircleColors(new int[]{R.color.blue}, getContext());
        dataSet.setDrawValues(false);

        dataSets.add(dataSet);
        analisis.add(new Analisis(1, "Area de cultivo 1", new LineData(dataSets)));

        return analisis;
    }

    private ArrayList<Entry> valuesData() {
        ArrayList<Entry> values = new ArrayList<>();
        String[][] dataDB;
        boolean connectAPI = api.isConnected();
        try {
            if (connectAPI){
                sql.clear(ConexionSQLite.TABLE_REGISTRO); // Limpiar tabla local
                Toast.makeText(getContext(), "Actualizando registro...", Toast.LENGTH_SHORT).show();
                dataDB = api.getData(ConexionAPI.TABLE_REGISTRO);   // Obtener datos
            }
            else {
                Toast.makeText(getContext(), "No se encuentra conectado.", Toast.LENGTH_SHORT).show();
                dataDB = sql.read(ConexionSQLite.TABLE_REGISTRO);
            }
            for (int i=0; i<dataDB.length; i++){    // Recorrer datos
                Registro r = new Registro(Long.parseLong(dataDB[i][0]), dataDB[i][1], Integer.parseInt(dataDB[i][2]),
                        Integer.parseInt(dataDB[i][3]), Integer.parseInt(dataDB[i][4]), Integer.parseInt(dataDB[i][5]),
                        Integer.parseInt(dataDB[i][6]));

                if (connectAPI) sql.insert(r);  // Agregar a la tabla local

                values.add(new Entry(Integer.parseInt(dataDB[i][0]), Integer.parseInt(dataDB[i][2])));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return values;
    }

    private ArrayList<Entry> valuesPrueba(){
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 20));
        values.add(new Entry(1, 30));
        values.add(new Entry(2, 5));
        values.add(new Entry(3, 10));
        values.add(new Entry(4, 25));
        values.add(new Entry(5, 15));

        return values;
    }
}
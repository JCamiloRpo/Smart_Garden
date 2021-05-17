package com.example.smartgarden.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.smartgarden.R;
import com.example.smartgarden.ui.adapters.AnalisisAdapter;
import com.example.smartgarden.ui.conexions.ConexionAPI;
import com.example.smartgarden.ui.conexions.ConexionSQLite;
import com.example.smartgarden.ui.entities.Analisis;
import com.example.smartgarden.ui.entities.Registro;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import static com.example.smartgarden.ui.activities.SplashActivity.api;
import static com.example.smartgarden.ui.activities.SplashActivity.sql;

public class AnalisisFragment extends Fragment {
    ListView list;
    AnalisisAdapter adapter;
    TextView txtNoData;
    boolean data;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_analisis, container, false);

        setUpView(root);
        return root;
    }

    private void setUpView(View v){
        Utils.init(getContext());
        list = v.findViewById(R.id.ListAnalisis);

        //Consultar datos
        adapter = new AnalisisAdapter(getActivity(), datos());
        list.setAdapter(adapter);

        txtNoData = v.findViewById(R.id.TxtNoData);
        if (data) {
            txtNoData.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }
        else {
            txtNoData.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }
    }

    private ArrayList<Analisis> datos(){
        ArrayList<Analisis> analisis = new ArrayList<>();
        ArrayList<Entry>[] values = valuesData();

        LineDataSet tem_suelo = new LineDataSet(values[0], "Tem Suelo");
        tem_suelo.setLineWidth(10);
        tem_suelo.setColors(new int[]{R.color.green}, getContext());
        tem_suelo.setDrawCircles(false);
        tem_suelo.setCircleRadius(6f);
        tem_suelo.setCircleHoleRadius(10f);
        tem_suelo.setCircleColors(new int[]{R.color.blue}, getContext());
        tem_suelo.setDrawValues(true);
        tem_suelo.setValueTextSize(10);

        LineDataSet tem_aire = new LineDataSet(values[1], "Tem Aire");
        tem_aire.setLineWidth(10);
        tem_aire.setColors(new int[]{R.color.blue_light}, getContext());
        tem_aire.setDrawCircles(false);
        tem_aire.setCircleRadius(6f);
        tem_aire.setCircleHoleRadius(10f);
        tem_aire.setCircleColors(new int[]{R.color.blue}, getContext());
        tem_aire.setDrawValues(true);
        tem_aire.setValueTextSize(10);

        LineDataSet hum_suelo = new LineDataSet(values[2], "Hum Suelo");
        hum_suelo.setLineWidth(10);
        hum_suelo.setColors(new int[]{R.color.yellow}, getContext());
        hum_suelo.setDrawCircles(false);
        hum_suelo.setCircleRadius(6f);
        hum_suelo.setCircleHoleRadius(10f);
        hum_suelo.setCircleColors(new int[]{R.color.blue}, getContext());
        hum_suelo.setDrawValues(true);
        hum_suelo.setValueTextSize(10);

        LineDataSet hum_aire = new LineDataSet(values[3], "Hum Aire");
        hum_aire.setLineWidth(10);
        hum_aire.setColors(new int[]{R.color.orange}, getContext());
        hum_aire.setDrawCircles(false);
        hum_aire.setCircleRadius(6f);
        hum_aire.setCircleHoleRadius(10f);
        hum_aire.setCircleColors(new int[]{R.color.blue}, getContext());
        hum_aire.setDrawValues(true);
        hum_aire.setValueTextSize(10);

        LineDataSet lux = new LineDataSet(values[4], "Lux");
        lux.setLineWidth(10);
        lux.setColors(new int[]{R.color.purple}, getContext());
        lux.setDrawCircles(false);
        lux.setCircleRadius(6f);
        lux.setCircleHoleRadius(10f);
        lux.setCircleColors(new int[]{R.color.blue}, getContext());
        lux.setDrawValues(true);
        lux.setValueTextSize(10);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(tem_suelo);
        dataSets.add(tem_aire);
        dataSets.add(hum_suelo);
        dataSets.add(hum_aire);
        dataSets.add(lux);

        analisis.add(new Analisis( tem_suelo.getEntryCount(), "Area de cultivo 1", new LineData(dataSets)));

        return analisis;
    }

    private ArrayList<Entry>[] valuesData() {
        ArrayList<Entry>[] values = new ArrayList[5];
        ArrayList<Entry> tem_suelo = new ArrayList<>(), tem_aire = new ArrayList<>(),
                hum_suelo = new ArrayList<>(), hum_aire = new ArrayList<>(), lux = new ArrayList<>();
        String[][] dataDB = new String[0][0];
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
            for (int i=dataDB.length-1, j=0; i > -1; i--, j++){    // Recorrer datos
                Registro r = new Registro(Long.parseLong(dataDB[i][0]), dataDB[i][1], Integer.parseInt(dataDB[i][2]),
                        Integer.parseInt(dataDB[i][3]), Integer.parseInt(dataDB[i][4]), Integer.parseInt(dataDB[i][5]),
                        Integer.parseInt(dataDB[i][6]));

                if (connectAPI) sql.insert(r);  // Agregar a la tabla local

                tem_suelo.add(new Entry(j, Integer.parseInt(dataDB[i][2])));
                tem_aire.add(new Entry(j, Integer.parseInt(dataDB[i][3])));
                hum_suelo.add(new Entry(j, Integer.parseInt(dataDB[i][4])));
                hum_aire.add(new Entry(j, Integer.parseInt(dataDB[i][5])));
                lux.add(new Entry(j, Integer.parseInt(dataDB[i][6])));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        values[0] = tem_suelo;
        values[1] = tem_aire;
        values[2] = hum_suelo;
        values[3] = hum_aire;
        values[4] = lux;
        data = !values[0].isEmpty();
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
package com.example.smartgarden.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.smartgarden.R;
import com.example.smartgarden.ui.activities.MainActivity;
import com.example.smartgarden.ui.adapters.AnalisisAdapter;
import com.example.smartgarden.ui.entities.Analisis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    ListView list;
    AnalisisAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        setUpView(root);
        return root;
    }

    private void setUpView(View v){
        list = v.findViewById(R.id.ListAnalisis);

        //Consultar datos
        adapter = new AnalisisAdapter(getActivity(), pruebaDatos());
        list.setAdapter(adapter);
    }

    private ArrayList<Analisis> pruebaDatos(){
        ArrayList<Analisis> analisis = new ArrayList<>();
        LineDataSet dataSet = new LineDataSet(valuesData(), "Datos 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSet.setLineWidth(100);
        dataSet.setColors(new int[]{R.color.green}, getContext());
        dataSet.setDrawCircles(false);
        dataSet.setValueTextSize(14);

        dataSets.add(dataSet);
        analisis.add(new Analisis(1, "Ánalisis de cultivo 1", new LineData(dataSets)));

        dataSet = new LineDataSet(valuesData(), "Datos 2");
        dataSets = new ArrayList<>();

        dataSet.setLineWidth(100);
        dataSet.setColors(new int[]{R.color.green}, getContext());
        dataSet.setDrawCircles(false);
        dataSet.setValueTextSize(14);

        dataSets.add(dataSet);
        analisis.add(new Analisis(2, "Ánalisis de cultivo 2", new LineData(dataSets)));

        dataSet = new LineDataSet(valuesData(), "Datos 3");
        dataSets = new ArrayList<>();

        dataSet.setLineWidth(100);
        dataSet.setColors(new int[]{R.color.green}, getContext());
        dataSet.setDrawCircles(false);
        dataSet.setValueTextSize(14);

        dataSets.add(dataSet);
        analisis.add(new Analisis(3, "Ánalisis de cultivo 3", new LineData(dataSets)));
        return analisis;
    }

    private ArrayList<Entry> valuesData(){
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
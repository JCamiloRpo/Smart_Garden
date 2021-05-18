package com.example.smartgarden.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.entities.Analisis;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class AnalisisDetalleFragment extends Fragment {
    public static Analisis analisis;
    TextView txtTitulo;
    Spinner spnIndividual, spnCombinada;
    LineChart chtIndividual, chtCombinada;
    BarChart chtBarra;
    // Variables de pruebas
    LineChart chtLine;
    PieChart chtPie;
    BarChart chtBar;

    public AnalisisDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_analisisdetalle, container, false);

        setUpView(root);
        return root;
    }

    private void setUpView(View v){
        //chtLine = v.findViewById(R.id.ChtLine);
        //chtPie = v.findViewById(R.id.ChtPie1);
        //chtBar = v.findViewById(R.id.ChtBar);
        txtTitulo = v.findViewById(R.id.TxtAnaTitulo);
        spnIndividual = v.findViewById(R.id.SpGraficaIndividual);
        spnIndividual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                graficaIndividual(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {  }
        });
        spnCombinada = v.findViewById(R.id.SpGraficaCombinada);
        spnCombinada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) graficaCombinada(position);
                else graficaCombinada(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        chtIndividual = v.findViewById(R.id.ChtLineIndividual);
        chtCombinada = v.findViewById(R.id.ChtLineCombinada);
        chtBarra = v.findViewById(R.id.ChtBar);
        txtTitulo.setText(analisis.getTitulo());

        setGraficaIndividual();
        setGraficaCombinada();
        setGraficaBarras();
    }

    private void graficaIndividual(int opcion){
        //Agregar dataset
        LineDataSet dataSet = (LineDataSet) analisis.getLineData().getDataSets().get(opcion);
        dataSet.setLineWidth(10);
        dataSet.setDrawValues(true);
        chtIndividual.setData(new LineData(dataSet));

        chtIndividual.animateY(2000, Easing.EaseInOutCubic);
        chtIndividual.invalidate();
    }

    private void setGraficaIndividual(){
        //Configurar grafica
        chtIndividual.setNoDataText("No se encontraron datos");
        chtIndividual.setDoubleTapToZoomEnabled(false);
        chtIndividual.setTouchEnabled(true);
        chtIndividual.zoom((float) (analisis.getAnalisisID()/10),0,  0,0);
        chtIndividual.setDescription(null);
        XAxis xAxis = chtIndividual.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        YAxis[] yAxis = {chtIndividual.getAxisLeft(), chtIndividual.getAxisRight()};
        yAxis[0].setDrawAxisLine(false);
        yAxis[0].setDrawGridLines(false);
        yAxis[0].setDrawLabels(false);
        yAxis[1].setDrawAxisLine(false);
        yAxis[1].setDrawGridLines(false);
        yAxis[1].setDrawLabels(false);
        chtIndividual.getLegend().setEnabled(false);

    }

    private void graficaCombinada(int opcion){
        //Agregar dataset
        LineDataSet dataSet1 = (LineDataSet) analisis.getLineData().getDataSets().get(opcion),
                    dataSet2 = (LineDataSet) analisis.getLineData().getDataSets().get(opcion+1);

        dataSet1.setLineWidth(10);
        dataSet1.setDrawValues(true);
        dataSet2.setLineWidth(10);
        dataSet2.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        chtCombinada.setData(new LineData(dataSets));

        chtCombinada.animateY(2000, Easing.EaseInOutCubic);
        chtCombinada.invalidate();
    }

    private void setGraficaCombinada(){
        //Configurar grafica
        chtCombinada.setNoDataText("No se encontraron datos");
        chtCombinada.setDoubleTapToZoomEnabled(false);
        chtCombinada.setTouchEnabled(true);
        chtCombinada.resetZoom();
        chtCombinada.zoom((float) (analisis.getAnalisisID()/10),0,  0,0);
        chtCombinada.setDescription(null);
        XAxis xAxis = chtCombinada.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        YAxis[] yAxis = {chtCombinada.getAxisLeft(), chtCombinada.getAxisRight()};
        yAxis[0].setDrawAxisLine(false);
        yAxis[0].setDrawGridLines(false);
        yAxis[0].setDrawLabels(false);
        yAxis[1].setDrawAxisLine(false);
        yAxis[1].setDrawGridLines(false);
        yAxis[1].setDrawLabels(false);
        chtCombinada.getLegend().setEnabled(true);
    }

    private void setGraficaBarras(){
        // Datos
        List<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, analisis.getLineData().getDataSets().get(0).getEntryForIndex(0).getY()));
        values.add(new BarEntry(1, analisis.getLineData().getDataSets().get(1).getEntryForIndex(0).getY()));
        values.add(new BarEntry(2, analisis.getLineData().getDataSets().get(2).getEntryForIndex(0).getY()));
        values.add(new BarEntry(3, analisis.getLineData().getDataSets().get(3).getEntryForIndex(0).getY()));
        values.add(new BarEntry(4, analisis.getLineData().getDataSets().get(4).getEntryForIndex(0).getY()));
        // DataSet
        BarDataSet dataSet = new BarDataSet(values, "Ultima medicion");
        dataSet.setValueTextSize(14);
        dataSet.setColors(new int[]{R.color.green, R.color.blue_light, R.color.yellow, R.color.orange, R.color.purple}, getContext());
        // Etiquetas
        ArrayList<String> etiquetas = new ArrayList<String>();
        etiquetas.add("Tem Suelo");
        etiquetas.add("Tem Aire");
        etiquetas.add("Hum Suelo");
        etiquetas.add("Hum Aire");
        etiquetas.add("Lux");
        //Agregar dataSet
        new BarData(dataSet);
        chtBarra.setData(new BarData(dataSet));
        //Configurar grafica
        chtBarra.setNoDataText("No se encontraron datos");
        chtBarra.setFitBars(false);
        chtBarra.setScaleEnabled(false);
        chtBarra.setDescription(null);
        XAxis xAxis = chtBarra.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(etiquetas));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        xAxis.setTextSize(14);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        YAxis[] yAxis = {chtBarra.getAxisLeft(), chtBarra.getAxisRight()};
        yAxis[0].setDrawAxisLine(false);
        yAxis[0].setDrawGridLines(false);
        yAxis[0].setDrawLabels(false);
        yAxis[1].setDrawAxisLine(false);
        yAxis[1].setDrawGridLines(false);
        yAxis[1].setDrawLabels(false);
        chtBarra.getLegend().setEnabled(false);

        chtBarra.animateY(2000, Easing.EaseInOutCubic);
        chtBarra.invalidate();
    }

    private void setLine() {
        //Datos
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 20));
        values.add(new Entry(1, 30));
        values.add(new Entry(2, 5));
        values.add(new Entry(3, 10));
        values.add(new Entry(4, 25));
        values.add(new Entry(5, 15));
        //Configurar dataset
        LineDataSet dataSet = new LineDataSet(values, "Datos 1");
        dataSet.setLineWidth(100);
        dataSet.setColors(new int[]{R.color.green}, getContext());
        dataSet.setDrawCircles(false);
        dataSet.setValueTextSize(14);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        //Agregar dataset
        chtLine.setData(new LineData(dataSets));
        //Configurar grafica
        chtLine.setNoDataText("No se encontraron datos");
        chtLine.setScaleEnabled(false);
        chtLine.setTouchEnabled(false);
        chtLine.setDescription(null);
        XAxis xAxis = chtLine.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        YAxis[] yAxis = {chtLine.getAxisLeft(), chtLine.getAxisRight()};
        yAxis[0].setDrawAxisLine(false);
        yAxis[0].setDrawGridLines(false);
        yAxis[0].setDrawLabels(false);
        yAxis[1].setDrawAxisLine(false);
        yAxis[1].setDrawGridLines(false);
        yAxis[1].setDrawLabels(false);
        chtLine.getLegend().setEnabled(false);

        chtLine.animateY(2000, Easing.EaseInOutCubic);
        chtLine.invalidate();
    }

    private void setPie() {
        //Datos
        ArrayList<PieEntry> values = new ArrayList();
        values.add(new PieEntry(15, "Lun"));
        values.add(new PieEntry(34, "Mar"));
        values.add(new PieEntry(85, "Mie"));
        values.add(new PieEntry(23, "Jue"));
        values.add(new PieEntry(17, "Vie"));
        values.add(new PieEntry(62, "Sab"));
        values.add(new PieEntry(40, "Dom"));
        //DataSet
        PieDataSet dataSet = new PieDataSet(values, "Dias de la semana");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setDrawValues(false);
        dataSet.setColors(new int[]{R.color.green, R.color.blue, R.color.purple, R.color.orange, R.color.yellow, R.color.green_light, R.color.black}, getContext());
        //Agregar DataSet
        chtPie.setData(new PieData(dataSet));
        //Configurar grafica
        chtPie.setNoDataText("No se encontraron datos");
        chtPie.setUsePercentValues(true);
        chtPie.getDescription().setEnabled(false);
        chtPie.setDrawEntryLabels(true);
        chtPie.setEntryLabelTextSize(14);
        chtPie.setEntryLabelColor(Color.BLACK);
        chtPie.getLegend().setEnabled(false);

        chtPie.setDragDecelerationFrictionCoef(0.99f);
        chtPie.setDrawHoleEnabled(true);
        chtPie.setHoleRadius(40f);
        chtPie.setHoleColor(Color.WHITE);
        chtPie.setTransparentCircleRadius(100f);

        chtPie.animateY(1000, Easing.EaseInOutCubic);
        chtPie.invalidate();
    }

    private void setBar() {
        //Datos
        List<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, 20));
        values.add(new BarEntry(1, 30));
        values.add(new BarEntry(2, 5));
        values.add(new BarEntry(3, 10));
        values.add(new BarEntry(4, 25));
        values.add(new BarEntry(5, 15));
        //DataSet
        BarDataSet dataSet = new BarDataSet(values, "Datos");
        dataSet.setValueTextSize(14);
        dataSet.setColors(new int[]{R.color.green, R.color.blue, R.color.purple, R.color.orange, R.color.yellow, R.color.green_light, R.color.black}, getContext());
        //Agregar dataSet
        chtBar.setData(new BarData(dataSet));
        //Configurar grafica
        chtBar.setNoDataText("No se encontraron datos");
        chtBar.setFitBars(true);
        chtBar.setScaleEnabled(false);
        chtBar.setDescription(null);
        XAxis xAxis = chtBar.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        YAxis[] yAxis = {chtBar.getAxisLeft(), chtBar.getAxisRight()};
        yAxis[0].setDrawAxisLine(false);
        yAxis[0].setDrawGridLines(false);
        yAxis[0].setDrawLabels(false);
        yAxis[1].setDrawAxisLine(false);
        yAxis[1].setDrawGridLines(false);
        yAxis[1].setDrawLabels(false);
        chtBar.getLegend().setEnabled(false);

        chtBar.animateY(2000, Easing.EaseInOutCubic);
        chtBar.invalidate();
    }
}